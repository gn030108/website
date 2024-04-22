package backend.musinsa.controller;


import backend.musinsa.domain.comment.CommentDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("write")
    public ResponseEntity<ApiResult> createComment(@RequestBody CommentDto commentDto){

        return commentService.createComment(commentDto);

    }

    @PostMapping("{id}/delete")
    public ResponseEntity<ApiResult> deleteComment(@RequestBody CommentDto commentDto){

        return commentService.deleteComment(commentDto);

    }

}
