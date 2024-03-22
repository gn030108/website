package backend.musinsa.domain.coupon;


import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "coupon_id")
    private Coupon coupon;

}
