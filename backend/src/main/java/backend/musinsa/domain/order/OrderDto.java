package backend.musinsa.domain.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {

    private String id;
    private String itemId;
    private Integer quantity;       //수량
    private Integer price;          //가격
    private String itemColorOption; //상품 색상 옵션
    private String itemSizeOption;  //상품 크기 옵션
    private Integer savedAmount;    //할인받은 양
    private String thumbnail;       //썸네일 주소
    private Boolean reviewState;    //리뷰 유무
    private Integer orderNumber;    //주문번호
    private String deliveryAddress;     //배송받을 주소
    private String memo;                //메모
    private String paymentAmount;       //지불할 총가격



}
