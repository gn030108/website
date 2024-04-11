package backend.musinsa.service;

import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.comment.Comment;
import backend.musinsa.domain.comment.CommentDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.NotFoundInformationException;
import backend.musinsa.domain.exception.TokenWithOutCredentials;
import backend.musinsa.domain.item.Review;
import backend.musinsa.repository.CommentRepository;
import backend.musinsa.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public ResponseEntity<ApiResult> createComment(CommentDto commentDto){
        try {
            Review review = reviewRepository.findById(Long.parseLong(commentDto.getReviewId()))
                    .orElseThrow();
            Comment comment = Comment.builder()
                    .writer(SecurityUtil.getCurrentUsername())
                    .text(commentDto.getText())
                    .review(review)
                    .build();
            commentRepository.save(comment);
        }catch (NoSuchElementException e){
            throw new NotFoundInformationException(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("댓글작성이 완료되었습니다.").build());
    }
    public ResponseEntity<ApiResult> deleteComment(CommentDto commentDto){
        try{

            Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow();

            if(!comment.getWriter().equals(SecurityUtil.getCurrentUsername())){
                throw new TokenWithOutCredentials(ExceptionEnum.ACCESS_DENIED_EXCEPTION);
            }
            commentRepository.delete(comment);

        } catch (IllegalArgumentException e){
            throw new NotFoundInformationException(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION);
        }
        return ResponseEntity.ok().body(ApiResult.builder().status("success").message("댓글삭제가 완료되었습니다.").build());
    }


}
