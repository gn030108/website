package backend.musinsa.repository;

import backend.musinsa.domain.item.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Favorite,Long> {
}
