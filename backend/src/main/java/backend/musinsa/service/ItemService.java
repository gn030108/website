package backend.musinsa.service;

import backend.musinsa.domain.SearchCond;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ItemException;
import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.item.ItemInfo;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.domain.item.ItemResponseDto;
import backend.musinsa.repository.CustomSearchRepository;
import backend.musinsa.repository.ItemInfoRepository;
import backend.musinsa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    private final CustomSearchRepository customSearchRepository;

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
                .id(item.getId())
                .price(item.getItemInfo().getPrice())
                .colorOption(item.getItemInfo().getColorOption())
                .sizeOption(item.getItemInfo().getSizeOption())
                .gender(item.getItemInfo().getGender())
                .status(item.getItemInfo().getStatus())
                .build();
    }


    public ResponseEntity<ApiResult> updateThumbnailImage(String id, List<MultipartFile> thumbnailImageList) {
        if(imageService.updateThumbnailImage(getItem(id),thumbnailImageList)){
            return ResponseEntity.ok().body(ApiResult.builder().status("success").message("썸네일 이미지 변경완료").build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResult.builder().status("error").message("썸네일 이미지 변경실패").build());
    }
    public ResponseEntity<ApiResult> updateMainImage(String id, List<MultipartFile> mainImageList){
        if(imageService.updateMainImage(getItem(id),mainImageList)){
            return ResponseEntity.ok().body(ApiResult.builder().status("success").message("메인 이미지 변경완료").build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResult.builder().status("error").message("메인 이미지 변경실패").build());
    }

    public Page<ItemResponseDto> searchItemDto(SearchCond searchCond, Pageable pageable){
        return customSearchRepository.searchItem(searchCond,pageable);
    }



}

