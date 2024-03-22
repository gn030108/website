package backend.musinsa.domain.order;


import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;   //주문 총수량
    private Member member;
    private Integer orderNumber;    //주문번호

    @Enumerated(EnumType.STRING)
    private OrderState order_state;    //주문상태

    private String deliveryAddress;     //배송받을 주소

    private String memo;        //메모
    private String savedAmount;        //할인받은 총가격
    private String paymentAmount;       //지불할 총가격
    private String totalAmount;     //상품 총 가격

}
