package backend.musinsa.domain.order;


import backend.musinsa.domain.coupon.CouponHistory;
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
    private String itemOption1;
    private String itemOption2;
    private Integer savedAmount;

    @Lob
    private String thumbnail;

    private List<CouponHistory> usedCouponList;



}
