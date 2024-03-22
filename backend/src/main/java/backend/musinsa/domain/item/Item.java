package backend.musinsa.domain.item;

import backend.musinsa.domain.board.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String tag;

    @OneToOne(mappedBy = "item",fetch = FetchType.LAZY)
    private ItemInfo itemInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "item")
    private List<Like> likeList;

    @OneToMany(mappedBy = "item")
    private List<Review> reviewList;


}
