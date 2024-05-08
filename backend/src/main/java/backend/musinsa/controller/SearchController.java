package backend.musinsa.controller;


import backend.musinsa.domain.SearchCond;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.domain.item.ItemResponseDto;
import backend.musinsa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("item")
public class SearchController {

    private final ItemService itemService;

    @GetMapping("get")
    public Page<ItemResponseDto> searchItem(@RequestBody SearchCond searchCond, Pageable pageable){
        return itemService.searchItemDto(searchCond,pageable);
    }
    @GetMapping("get/{id}")
    public ItemRequestDto getItemInfo(@RequestParam("id") String id){
        return itemService.getItemRequestDto(id);
    }



}
