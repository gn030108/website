package backend.musinsa.domain.coupon;


import backend.musinsa.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean usable;

    private LocalDateTime expiredTime;  //만료기간

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "coupon_info_id")
    private CouponInfo couponInfo;

    @OneToOne(mappedBy = "coupon")
    private CouponHistory couponHistory;

}
