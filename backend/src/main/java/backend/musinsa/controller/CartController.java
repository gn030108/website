package backend.musinsa.controller;


import backend.musinsa.domain.cart.CartDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.order.OrderDto;
import backend.musinsa.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("cart")
public class CartController {

    private final CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<ApiResult> storeCartItem(@RequestBody OrderDto orderDto){
        return cartService.addCartItem(orderDto);
        //OrderDto 안에있는 itemId를 통해 해당 Item 조회.
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResult> deleteCartItem(@RequestBody OrderDto orderDto){
        return cartService.deleteCartItem(Long.valueOf(orderDto.getItemId()));
    }

    @GetMapping("/get")
    public List<CartDto> getCartItem(){
        return cartService.getCartItem();
    }

}
