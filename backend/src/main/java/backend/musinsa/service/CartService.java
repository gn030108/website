package backend.musinsa.service;


import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.cart.CartDto;
import backend.musinsa.domain.cart.CartItem;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.TokenWithOutCredentials;
import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.order.OrderDto;
import backend.musinsa.domain.order.OrderItem;
import backend.musinsa.repository.CartItemRepository;
import backend.musinsa.repository.MemberRepository;
import backend.musinsa.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;
    /**
     * 장바구니에 상품 추가
     *
     * @param orderDto
     * @return
     */
    public ResponseEntity<ApiResult> addCartItem(OrderDto orderDto){
        try{

            Item item = itemService.getItem(String.valueOf(orderDto.getItemId()));
            OrderItem orderItem = OrderItem.builder()
                    .quantity(orderDto.getQuantity())
                    .price(item.getItemInfo().getPrice())
                    .itemName(item.getName())
                    .itemColorOption(orderDto.getItemColorOption())
                    .itemSizeOption(orderDto.getItemSizeOption())
                    .thumbnail(item.getImage().getThumbnailImageUrlList().get(0))
                    .build();
            Member member= memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();
            CartItem cartItem = CartItem.builder()
                    .orderItemList(orderItem)
                    .member(member)
                    .build();
            orderItemRepository.save(orderItem);
            cartItemRepository.save(cartItem);
        } catch (NoSuchElementException e){
            // 시큐리티에서 인증정보를 가져오지못함.
            throw new TokenWithOutCredentials(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("장바구니에 상품 담기 완료").build());

    }
    public ResponseEntity<ApiResult> deleteCartItem(Long id){
        try{
            Member member= memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();
            List<OrderItem> orderItemList = member.getCart().getOrderItemList();
            orderItemList.remove(orderItemList.stream()
                    .filter( o -> o.getId().equals(id))
                    .findFirst().orElseThrow());
        }catch (NoSuchElementException e){
            throw new TokenWithOutCredentials(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("장바구니에 담겨있는 상품 삭제완료").build());
    }

    public List<CartDto> getCartItem(){
        Member member= memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();
        return member.getCart().getOrderItemList()
                .stream()
                .map(OrderItem::toCartDto)
                .toList();
    }


}
