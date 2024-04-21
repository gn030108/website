package backend.musinsa.domain.item;


import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ItemInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private String status;

    private String gender;

    private String itemNumber;

    private List<String> colorOption;

    private List<String> sizeOption;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemInfo(Integer price, String status, String gender, String itemNumber,
                    List<String> colorOption, List<String> sizeOption) {
        this.price = price;
        this.status = status;
        this.gender = gender;
        this.itemNumber = itemNumber;
        this.colorOption = colorOption;
        this.sizeOption = sizeOption;
    }
}
