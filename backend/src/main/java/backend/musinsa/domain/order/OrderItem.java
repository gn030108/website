package backend.musinsa.domain.order;


import backend.musinsa.domain.coupon.CouponHistory;
import backend.musinsa.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @JoinColumn(name = "member_order_id")
    private MemberOrder memberOrder;

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
}
