package backend.musinsa.repository;

import backend.musinsa.domain.item.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
