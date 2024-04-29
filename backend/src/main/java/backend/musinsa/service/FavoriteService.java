package backend.musinsa.service;

import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.FailFavoriteItemAddException;
import backend.musinsa.domain.item.Favorite;
import backend.musinsa.domain.item.Item;
import backend.musinsa.domain.member.Member;
import backend.musinsa.repository.ItemRepository;
import backend.musinsa.repository.LikeRepository;
import backend.musinsa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {

    private final LikeRepository likeRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;


    public ResponseEntity<ApiResult> addLike(String itemId){
        try {
            Member member = memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();
            Item item = itemRepository.findById(Long.parseLong(itemId)).orElseThrow();
            Favorite favorite = Favorite.builder()
                    .member(member)
                    .item(item)
                    .build();
            likeRepository.save(favorite);
        } catch (NoSuchElementException e){
            throw new FailFavoriteItemAddException(ExceptionEnum.FAIL_FAVORITE_ITEM_ADD);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("상품 좋아요 성공").build());
    }

    public void deleteLike(String likeId){

    }
}

