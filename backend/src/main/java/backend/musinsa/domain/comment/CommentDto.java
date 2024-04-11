package backend.musinsa.domain.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDto {

    private Long id;
    private String reviewId;
    private String name;
    private String text;

}
