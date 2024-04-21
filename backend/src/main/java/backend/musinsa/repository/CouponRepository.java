package backend.musinsa.repository;

import backend.musinsa.domain.coupon.Coupon;
import backend.musinsa.domain.member.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    @Query("SELECT c FROM Coupon c WHERE c.member = :member AND c.usable = false")
    List<Coupon> findUnusedCouponsByMember(@Param("member") Member member);
}
