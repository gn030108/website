package backend.musinsa.domain.coupon;


import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CouponInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponName;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    private Integer discountValue;

    @OneToMany(mappedBy = "couponInfo")
    private List<Coupon> coupon;

}
