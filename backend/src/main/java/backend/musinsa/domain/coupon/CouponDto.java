package backend.musinsa.domain.coupon;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDto {

    private Long id;

    private String couponName;

    private CouponType couponType;

    private Integer discountValue;

    private LocalDateTime expiredTime;  //만료기간

    private Boolean used;

    public static CouponDto toDto(Coupon coupon){
        return CouponDto.builder()
                .id(coupon.getId())
                .couponName(coupon.getCouponInfo().getCouponName())
                .couponType(coupon.getCouponInfo().getCouponType())
                .discountValue(coupon.getCouponInfo().getDiscountValue())
                .expiredTime(coupon.getExpiredTime())
                .build();
    }


}
