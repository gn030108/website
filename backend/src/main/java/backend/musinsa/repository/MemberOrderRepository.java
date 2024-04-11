package backend.musinsa.repository;

import backend.musinsa.domain.order.MemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOrderRepository extends JpaRepository<MemberOrder,Long> {

}
