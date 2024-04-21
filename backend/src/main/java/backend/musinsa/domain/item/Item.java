package backend.musinsa.domain.item;

import backend.musinsa.domain.board.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    private String brand;

    @OneToOne(mappedBy = "item",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private ItemInfo itemInfo;

    @OneToOne(mappedBy = "item",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Image image;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "item")
    private List<Favorite> favoriteList;

    @OneToMany(mappedBy = "item")
    private List<Review> reviewList;
    @Builder
    public Item(String name, String category, String tag, String brand, ItemInfo itemInfo,Image image) {
        this.name = name;
        this.category = category;
        this.tag = tag;
        this.brand = brand;
        this.itemInfo = itemInfo;
        this.image = image;
    }
    public void updateItem(String name, String category, String tag, String brand, ItemInfo itemInfo) {
        this.name = name;
        this.category = category;
        this.tag = tag;
        this.brand = brand;
        this.itemInfo = itemInfo;

    }

    public void setImage(Image image) {
        this.image = image;
    }
}
