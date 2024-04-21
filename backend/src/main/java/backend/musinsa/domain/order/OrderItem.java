package backend.musinsa.domain.order;


import backend.musinsa.domain.cart.CartDto;
import backend.musinsa.domain.cart.CartItem;
import backend.musinsa.domain.coupon.CouponHistory;
import backend.musinsa.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Integer price;
    private String itemName;
    private String itemColorOption;
    private String itemSizeOption;
    private Integer savedAmount;
    private String thumbnail;
    private Boolean reviewState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item")
    private CartItem cartItem;

    @OneToMany(mappedBy = "orderItem",fetch = FetchType.LAZY)
    private List<CouponHistory> usedCouponList;

    @OneToOne
    @JoinColumn(name = "item_id")       //단방향
    private Item item;

    public static OrderItem getOrder(List<OrderItem> orderItemList,Long id){
        for (OrderItem orderItem : orderItemList) {
            if(orderItem.getId().equals(id)){
                return orderItem;
            }
        }
        return null;
    }

    public void setReviewState(Boolean reviewState) {
        this.reviewState = reviewState;
    }
    @Builder
    public OrderItem(Integer quantity, Integer price, String itemName, String itemColorOption,
                     String itemSizeOption, Integer savedAmount, String thumbnail, Boolean reviewState,
                     CartItem cart, List<CouponHistory> usedCouponList, Item item) {
        this.quantity = quantity;
        this.price = price;
        this.itemName = itemName;
        this.itemColorOption = itemColorOption;
        this.itemSizeOption = itemSizeOption;
        this.savedAmount = savedAmount;
        this.thumbnail = thumbnail;
        this.reviewState = reviewState;
        this.cartItem = cart;
        this.usedCouponList = usedCouponList;
        this.item = item;
    }

    public static CartDto toCartDto(OrderItem orderItem){
        return CartDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .itemName(orderItem.getItemName())
                .itemColorOption(orderItem.getItemColorOption())
                .itemSizeOption(orderItem.getItemSizeOption())
                .thumbnail(orderItem.getThumbnail())
                .build();
    }

}
