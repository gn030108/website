package backend.musinsa.domain.cart;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    //주문목록 조회를 위해서 만듬
    private Long id;
    private Integer quantity;       //수량
    private Integer price;          //가격
    private String itemName;
    private String itemColorOption;
    private String itemSizeOption;
    private Integer savedAmount;
    private String thumbnail;
    private Boolean reviewState;

}

