package backend.musinsa.service;

import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ItemException;
import backend.musinsa.domain.item.Image;
import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.item.ItemInfo;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.repository.ItemInfoRepository;
import backend.musinsa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemInfoRepository itemInfoRepository;
    private final ImageService imageService;

    public ResponseEntity<ApiResult> storeItem(ItemRequestDto input, List<MultipartFile> mainImageList,
                                               List<MultipartFile> thumbnailImageList){
        //상품 등록 메서드
        try{

            Item item = itemRepository.save(
                    Item.builder()
                            .name(input.getName())
                            .brand(input.getBrand())
                            .category(input.getCategory())
                            .tag(input.getTag())
                            .itemInfo(
                                    itemInfoRepository.save(
                                            ItemInfo.builder()
                                                    .itemNumber(input.getItemNumber())
                                                    .price(input.getPrice())
                                                    .gender(input.getGender())
                                                    .status(input.getStatus())
                                                    .colorOption(input.getColorOption())
                                                    .sizeOption(input.getSizeOption())
                                                    .build()))
                            .build());
            item.setImage(imageService.storeImage(mainImageList, thumbnailImageList, item));
        }
        catch (IllegalArgumentException e){
            throw new ItemException(ExceptionEnum.ITEM_REGISTRATION_FAIL);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("상품 등록 완료").build());
    }

    public void deleteItem(String id){
        //상품 정보 삭제 메서드
        imageService.deleteImage(Long.parseLong(id),getItem(id));
        itemRepository.deleteById(Long.parseLong(id));

    }
    public ResponseEntity<ApiResult> updateItem(ItemRequestDto input, String id){
        //상품 정보 변경 메서드
        try {
            Item item = getItem(id);
            item.updateItem(input.getName(), input.getCategory(), input.getTag(), input.getBrand(),
                    ItemInfo.builder()
                            .itemNumber(input.getItemNumber())
                            .price(input.getPrice())
                            .status(input.getStatus())
                            .gender(input.getGender())
                            .colorOption(input.getColorOption())
                            .sizeOption(input.getSizeOption())
                            .build());
        } catch (Exception e){
            throw new ItemException(ExceptionEnum.ITEM_UPDATE_FAIL);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("상품정보 변경 완료").build());
    }
    public Item getItem(String id){
        //상품 단일 조회 메서드
        return itemRepository.findById(Long.parseLong(id)).orElseGet(Item::new);
    }
    public ItemRequestDto getItemRequestDto(String id){
        Item item = getItem(id);
        return ItemRequestDto.builder()
                .brand(item.getBrand())
                .name(item.getName())
                .tag(item.getTag())
                .category(item.getCategory())
                .price(item.getItemInfo().getPrice())
                .itemNumber(item.getItemInfo().getItemNumber())
                .colorOption(item.getItemInfo().getColorOption())
                .sizeOption(item.getItemInfo().getSizeOption())
                .gender(item.getItemInfo().getGender())
                .status(item.getItemInfo().getStatus())
                .build();
    }



}
