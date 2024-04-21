package backend.musinsa.repository;

import backend.musinsa.domain.coupon.CouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponHistoryRepository extends JpaRepository<CouponHistory,Long> {
}
