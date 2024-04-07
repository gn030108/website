package backend.musinsa.controller;

import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController("admin/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("store")
    public ResponseEntity<ApiResult> itemStore(@RequestBody ItemRequestDto input,
                                               @RequestPart("thumbnail") List<MultipartFile> thumbnailImage,
                                               @RequestPart("main") List<MultipartFile> mainImage){
        return itemService.storeItem(input,mainImage,thumbnailImage);
    }

    @GetMapping("delete/{id}")
    public void itemDelete(@RequestParam("id") String id){
        itemService.deleteItem(id);
    }

    @GetMapping("update/{id}")
    public ItemRequestDto getItemInfo(@RequestParam("id") String id){
        return itemService.getItemRequestDto(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<ApiResult> itemUpdate(@RequestParam("id") String id,
                                                @RequestBody ItemRequestDto input){

        return itemService.updateItem(input,id);

    }

    @PostMapping("update/thumbnailImage")
    public void updateThumbnailImage(){

    }

    @PostMapping("update/mainImage")
    public void updateMainImage(){

    }


}
