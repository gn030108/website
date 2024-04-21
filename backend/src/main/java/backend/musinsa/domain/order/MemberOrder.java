package backend.musinsa.domain.order;


import backend.musinsa.domain.cart.CartItem;
import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberOrder extends BaseTimeEntity {

    @Column(name = "member_order_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;   //주문 총수량

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;    //주문번호

    private String deliveryAddress;     //배송받을 주소
    private String memo;        //메모
    private Integer savedAmount;        //할인받은 총가격
    private Integer paymentAmount;       //지불할 총가격
    private Integer totalAmount;     //상품 총 가격

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "memberOrder")
    private CartItem cartItem;

    @Enumerated(EnumType.STRING)
    private OrderState order_state;    //주문상태

    @Builder
    public MemberOrder(Integer quantity, String deliveryAddress, String memo, Integer savedAmount, Integer paymentAmount, Integer totalAmount, Member member, CartItem cartItem, OrderState order_state) {
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
        this.memo = memo;
        this.savedAmount = savedAmount;
        this.paymentAmount = paymentAmount;
        this.totalAmount = totalAmount;
        this.member = member;
        this.cartItem = cartItem;
        this.order_state = order_state;
    }
}
