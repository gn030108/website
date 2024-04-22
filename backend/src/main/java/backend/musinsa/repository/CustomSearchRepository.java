package backend.musinsa.repository;

import backend.musinsa.domain.SearchCond;
import backend.musinsa.domain.item.ItemResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSearchRepository {

    Page<ItemResponseDto> searchItem(SearchCond boardSearch, Pageable pageable);

}
