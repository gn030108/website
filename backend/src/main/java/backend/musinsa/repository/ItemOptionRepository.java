package backend.musinsa.repository;

import backend.musinsa.domain.item.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption,Long> {
}
