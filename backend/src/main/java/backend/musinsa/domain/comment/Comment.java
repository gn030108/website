package backend.musinsa.domain.comment;


import backend.musinsa.domain.BaseTimeEntity;
import backend.musinsa.domain.item.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private String writer;

    private String text;

    @Builder
    public Comment(Review review, String writer, String text) {
        this.review = review;
        this.writer = writer;
        this.text = text;
    }
}
