package backend.musinsa.controller;


import backend.musinsa.domain.SearchCond;
import backend.musinsa.domain.item.CardItemDto;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.domain.item.ItemResponseDto;
import backend.musinsa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("item")
public class SearchController {

    private final ItemService itemService;

    @GetMapping("search")
    public Page<ItemResponseDto> searchItem(@RequestBody SearchCond searchCond, Pageable pageable){
        return itemService.searchItemDto(searchCond,pageable);
    }
    @GetMapping("get/{id}")
    public ItemRequestDto getItemInfo(@PathVariable("id") String id){
        return itemService.getItemRequestDto(id);
    }

    @GetMapping("get/home")
    public List<CardItemDto> getHome(){
        return itemService.getHomeItemDto();
    }



}
