package backend.musinsa.service;


import backend.musinsa.domain.item.Image;
import backend.musinsa.domain.item.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    public Image storeImage(List<MultipartFile> mainImageList, List<MultipartFile> thumbnailImageList, Item item);

    public Boolean updateThumbnailImage(Item item , List<MultipartFile> thumbnailImageList);

    public Boolean updateMainImage(Item item,List<MultipartFile> mainImageList);

    public void deleteImage(long id, Item item);
}
