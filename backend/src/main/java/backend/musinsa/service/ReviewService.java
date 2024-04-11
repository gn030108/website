package backend.musinsa.service;

import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.comment.CommentDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.NotFoundInformationException;
import backend.musinsa.domain.exception.TokenWithOutCredentials;
import backend.musinsa.domain.item.Review;
import backend.musinsa.domain.item.ReviewDto;
import backend.musinsa.domain.order.OrderItem;
import backend.musinsa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderItemRepository orderItemRepository;
    public ResponseEntity<ApiResult> createReview(ReviewDto reviewDto){
        try {
            OrderItem orderItem = orderItemRepository.findById(Long.parseLong(reviewDto.getOrderItemId()))
                    .orElseThrow();

            Review review = Review.toEntity(reviewDto, orderItem);

            reviewRepository.save(review);

            orderItem.setReviewState(true);
        }catch (NoSuchElementException e){
            throw new NotFoundInformationException(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION);
        }
        return ResponseEntity.ok().body(
                ApiResult.builder().status("success").message("정상적으로 리뷰작성이 완료되었습니다.")
                        .build());
    }

    public ResponseEntity<ApiResult> deleteReview(String reviewId){
        try {
            Review review = reviewRepository.findById(Long.parseLong(reviewId)).orElseThrow();

            if(!review.getName().equals(SecurityUtil.getCurrentUsername())){
                throw new TokenWithOutCredentials(ExceptionEnum.ACCESS_DENIED_EXCEPTION);
            }
            reviewRepository.delete(review);

        } catch (IllegalArgumentException e){
            throw new NotFoundInformationException(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION);
        }
        return ResponseEntity.ok().body(
                ApiResult.builder().status("success").message("정상적으로 리뷰 삭제가 완료되었습니다.")
                        .build());
    }

}
