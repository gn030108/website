package backend.musinsa.repository;

import backend.musinsa.domain.SearchCond;
import backend.musinsa.domain.item.ItemResponseDto;
import backend.musinsa.domain.item.QItem;
import backend.musinsa.domain.item.QItemInfo;
import backend.musinsa.domain.item.QItemResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CustomSearchRepositoryImpl implements CustomSearchRepository{

    private final JPAQueryFactory queryFactory;

    public CustomSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    // 검색어에 적용될수도 있는 것들.    
//    private String name;              제품명
//    private List<String> category;    카테고리
//    private String brand;             브랜드명
//    private List<String> tag;         태그
//    private Integer price;            가격
//    private String status;            제품판매 상태
//    private List<String> colorOption; 색상
//    private List<String> sizeOption;  크기
//    private String gender;            성별

    @Override
    public Page<ItemResponseDto> searchItem(SearchCond searchCond, Pageable pageable) {

        List<ItemResponseDto> result = queryFactory
                .select(new QItemResponseDto(
                        QItem.item.id,
                        QItem.item.name,
                        QItem.item.category,
                        QItem.item.brand,
                        QItem.item.tag,
                        QItemInfo.itemInfo.price,
                        QItemInfo.itemInfo.status,
                        QItemInfo.itemInfo.colorOption,
                        QItemInfo.itemInfo.sizeOption,
                        QItemInfo.itemInfo.gender
                ))
                .from(QItem.item)
                .join(QItemInfo.itemInfo)
                .where(
                        itemNameLike(searchCond.getName()),
                        itemCategoryEqual(searchCond.getCategory()),
                        brandEqual(searchCond.getBrand()),
                        tagEqual(searchCond.getTag()),
                        statusEqual(searchCond.getStatus()),
                        colorOptionEqual(searchCond.getColorOption()),
                        sizeOptionEqual(searchCond.getSizeOption()),
                        genderEqual(searchCond.getGender())
                )
                .offset(pageable.getOffset())
                .limit(18)
                .fetch();

        JPAQuery<Long> totalCount = queryFactory
                .select(QItem.item.count())
                .from(QItem.item)
                .where(
                        itemNameLike(searchCond.getName())
                );

        return PageableExecutionUtils.getPage(result,pageable,totalCount::fetchOne);
    }

    private BooleanExpression itemNameLike(String name){
        return StringUtils.hasText(name) ? QItem.item.name.like(name) : null;
    }
    private BooleanExpression itemCategoryEqual(List<String> category){
        if(!category.isEmpty() && category != null){
            return QItem.item.category.any().in(category);
        }
        return null;
    }
    private BooleanExpression brandEqual(String brand){
        return StringUtils.hasText(brand) ? QItem.item.brand.eq(brand) : null;
    }
    private BooleanExpression tagEqual(List<String> tag){
        if(!tag.isEmpty() && tag != null){
            return QItem.item.tag.any().in(tag);
        }
        return null;
    }
    private BooleanExpression statusEqual(String status){
        return StringUtils.hasText(status) ? QItemInfo.itemInfo.status.eq(status) : null;
    }
    private BooleanExpression colorOptionEqual(List<String> colorOptionList){
        if(!colorOptionList.isEmpty() && colorOptionList != null){
            return QItemInfo.itemInfo.colorOption.any().in(colorOptionList);
        }
        return null;
    }
    private BooleanExpression sizeOptionEqual(List<String> sizeOptionList){
        if(!sizeOptionList.isEmpty() && sizeOptionList != null){
            return QItemInfo.itemInfo.sizeOption.any().in(sizeOptionList);
        }
        return null;
    }
    private BooleanExpression genderEqual(String gender){
        return StringUtils.hasText(gender) ? QItemInfo.itemInfo.gender.eq(gender) : null;
    }

//    private BooleanExpression Price()


}
