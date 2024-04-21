package backend.musinsa.repository;

import backend.musinsa.domain.item.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInfoRepository extends JpaRepository<ItemInfo,Long> {
}
