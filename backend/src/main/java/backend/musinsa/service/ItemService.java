package backend.musinsa.service;

import backend.musinsa.repository.ItemInfoRepository;
import backend.musinsa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemInfoRepository itemInfoRepository;




}
