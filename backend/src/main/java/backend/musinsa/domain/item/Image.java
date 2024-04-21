package backend.musinsa.domain.item;


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
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<String> itemImageUrlList;

    private List<String> thumbnailImageUrlList;


    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Image(List<String> itemImageUrlList, List<String> thumbnailImageUrlList, Item item) {
        this.itemImageUrlList = itemImageUrlList;
        this.thumbnailImageUrlList = thumbnailImageUrlList;
        this.item = item;
    }

    public void setItemImageUrlList(List<String> itemImageUrlList) {
        this.itemImageUrlList = itemImageUrlList;
    }

    public void setThumbnailImageUrlList(List<String> thumbnailImageUrlList) {
        this.thumbnailImageUrlList = thumbnailImageUrlList;
    }
}
