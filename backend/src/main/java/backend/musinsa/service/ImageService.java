package backend.musinsa.service;


import backend.musinsa.domain.board.Board;
import backend.musinsa.domain.item.Image;
import backend.musinsa.domain.item.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    public Image storeImage(List<MultipartFile> mainImageList, List<MultipartFile> thumbnailImageList, Item item);

    public Image updateImage(Item item,List<MultipartFile> mainImageList, List<MultipartFile> thumbnailImageList);

    public void deleteImage(long id, Item item);
}
