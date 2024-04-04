package backend.musinsa.service;

import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ItemException;
import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.item.ItemInfo;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.repository.ItemInfoRepository;
import backend.musinsa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemInfoRepository itemInfoRepository;

    public ResponseEntity<ApiResult> storeItem(ItemRequestDto input){
        //상품 등록 메서드
        try{
            itemRepository.save(
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
                                .itemImageUrl(input.getImageUrl())
                                .thumbnailImageUrl(input.getThumbnailImageUrl())
                                .build()))
                        .build());
        }
        catch (IllegalArgumentException e){
            throw new ItemException(ExceptionEnum.ITEM_REGISTRATION_FAIL);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("상품 등록 완료").build());
    }

    public void deleteItem(String id){
        //상품 정보 삭제 메서드

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
                            .itemImageUrl(input.getImageUrl())
                            .thumbnailImageUrl(input.getThumbnailImageUrl())
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
                .imageUrl(item.getItemInfo().getItemImageUrl())
                .thumbnailImageUrl(item.getItemInfo().getThumbnailImageUrl())
                .colorOption(item.getItemInfo().getColorOption())
                .sizeOption(item.getItemInfo().getSizeOption())
                .gender(item.getItemInfo().getGender())
                .status(item.getItemInfo().getStatus())
                .build();
    }



}
