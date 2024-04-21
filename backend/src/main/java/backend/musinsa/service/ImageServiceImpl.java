package backend.musinsa.service;

import backend.musinsa.domain.item.Image;
import backend.musinsa.domain.item.Item;
import backend.musinsa.repository.ImageRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final String keyFileName = "firebasestrorage-5a681f7fdfba.json";
    @Override
    public Image storeImage(List<MultipartFile> mainImageList, List<MultipartFile> thumbnailImageList, Item item) {
        try {
            InputStream keyFile = ResourceUtils.getURL("classpath:"+keyFileName).openStream();


            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFile))
                    .build()
                    .getService();

            Image image = Image.builder()
                    .itemImageUrlList(mainImageStore(mainImageList, storage))
                    .thumbnailImageUrlList(thumbnailImageStore(thumbnailImageList, storage))
                    .item(item)
                    .build();
            imageRepository.save(image);

            return image;
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private List<String> thumbnailImageStore(List<MultipartFile> thumbnailImageList, Storage storage) throws IOException {
        List<String> imageList= new ArrayList<>();

        for (MultipartFile multipartFile : thumbnailImageList) {
            String uuid = LocalDateTime.now().toString() + "_" + UUID.randomUUID();
            String ext = multipartFile.getOriginalFilename().split("\\.")[1];
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                    .setContentType(ext).build();
            if (!ext.equals("png")) {
                if(!ext.equals("jpg")){
                    log.info("이미지 형식이맞지않습니다.");
                }
            }
            storage.createFrom(blobInfo, multipartFile.getInputStream());
            imageList.add(uuid);
        }
        return imageList;
    }

    private List<String> mainImageStore(List<MultipartFile> mainImageList, Storage storage) throws IOException {
        List<String> imageList= new ArrayList<>();

        for (MultipartFile multipartFile : mainImageList) {
            String uuid = LocalDateTime.now().toString() + "_" + UUID.randomUUID();
            String ext = multipartFile.getOriginalFilename().split("\\.")[1];
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                    .setContentType(ext).build();
            if (!ext.equals("png")) {
                if(!ext.equals("jpg")){
                    log.info("이미지 형식이맞지않습니다.");
                }
            }
            storage.createFrom(blobInfo, multipartFile.getInputStream());
            imageList.add(uuid);
        }
        return imageList;
    }
    @Override
    public Boolean updateThumbnailImage(Item item, List<MultipartFile> thumbnailImageList) {

        try {
            List<String> thumbnailImageUrlList = item.getImage().getThumbnailImageUrlList();
            InputStream keyFile = ResourceUtils.getURL("classpath:"+keyFileName).openStream();
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFile))
                    .build()
                    .getService();
            for (String image : thumbnailImageUrlList) {

                Blob blob = storage.get(bucketName, image);

                if(blob == null){
                    log.info("The object "+ image + " wasn't fount in "+bucketName);
                }
                Storage.BlobSourceOption precondition =
                        Storage.BlobSourceOption.generationMatch(blob.getGeneration());

                storage.delete(bucketName, image, precondition);
            }
            item.getImage().setThumbnailImageUrlList(thumbnailImageStore(thumbnailImageList,storage));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean updateMainImage(Item item, List<MultipartFile> mainImageList) {
        try {
            List<String> itemImageUrlList = item.getImage().getItemImageUrlList();
            InputStream keyFile = ResourceUtils.getURL("classpath:"+keyFileName).openStream();
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFile))
                    .build()
                    .getService();
            for (String image : itemImageUrlList) {

                Blob blob = storage.get(bucketName, image);

                if(blob == null){
                    log.info("The object "+ image + " wasn't fount in "+bucketName);
                }
                Storage.BlobSourceOption precondition =
                        Storage.BlobSourceOption.generationMatch(blob.getGeneration());

                storage.delete(bucketName, image, precondition);
            }
            item.getImage().setItemImageUrlList(mainImageStore(mainImageList,storage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void deleteImage(long id, Item item) {
        try {
            InputStream keyFile = ResourceUtils.getURL("classpath:"+keyFileName).openStream();
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFile))
                    .build()
                    .getService();
            delete(item, storage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(Item item, Storage storage) {
        Image images = item.getImage();
        for (String image : images.getItemImageUrlList()) {

            Blob blob = storage.get(bucketName, image);
            if(blob == null){
                log.info("The object "+ image + " wasn't fount in "+bucketName);
                return;
            }
            Storage.BlobSourceOption precondition =
                    Storage.BlobSourceOption.generationMatch(blob.getGeneration());

            storage.delete(bucketName, image, precondition);
        }

        for (String image : images.getThumbnailImageUrlList()) {
            Blob blob = storage.get(bucketName, image);
            if(blob == null){
                log.info("The object "+ image + " wasn't fount in "+bucketName);
                return;
            }
            Storage.BlobSourceOption precondition =
                    Storage.BlobSourceOption.generationMatch(blob.getGeneration());

            storage.delete(bucketName, image, precondition);
        }
    }
}
