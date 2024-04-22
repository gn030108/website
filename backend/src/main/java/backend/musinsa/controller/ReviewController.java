package backend.musinsa.controller;


import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.item.ReviewDto;
import backend.musinsa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("write")
    public ResponseEntity<ApiResult> createReview(@RequestBody ReviewDto reviewDto){
        return reviewService.createReview(reviewDto);
    }

    @GetMapping("{id}/delete")
    public ResponseEntity<ApiResult> deleteReview(@PathVariable("id") String id){
        return reviewService.deleteReview(id);
    }


}
