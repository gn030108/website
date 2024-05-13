package backend.musinsa.config;


import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.item.ItemInfo;
import backend.musinsa.domain.item.ItemRequestDto;
import backend.musinsa.repository.ItemInfoRepository;
import backend.musinsa.repository.ItemRepository;
import backend.musinsa.service.ItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class PostConstruct {

    private final InitItemService initItemService;

    @jakarta.annotation.PostConstruct
    public void init(){
        initItemService.init();
    }

    @Component
    static class InitItemService{

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        ItemInfoRepository itemInfoRepository;

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            for(int i = 100; i<115; i=i+5) {
                ItemInfo itemInfo = ItemInfo.builder()
                        .price(i * 100)
                        .gender("성별")
                        .colorOption(List.of("빨강", "파랑"))
                        .sizeOption(List.of(String.valueOf(i), String.valueOf(i + 5)))
                        .build();
                Item item = Item.builder()
                        .name("테스트" + i)
                        .brand("테스트")
                        .category(List.of("테스트", String.valueOf(i)))
                        .tag(List.of("테스트"))
                        .itemInfo(itemInfo)
                        .build();

                itemInfoRepository.save(itemInfo);
                itemRepository.save(item);
                log.info("등록완료 - > "+item.toString());
            }
        }

    }





}
