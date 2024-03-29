package backend.musinsa.repository;

import backend.musinsa.domain.order.MemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MemberOrder,Long> {
}
