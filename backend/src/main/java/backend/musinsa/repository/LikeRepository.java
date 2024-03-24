package backend.musinsa.repository;

import backend.musinsa.domain.item.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
