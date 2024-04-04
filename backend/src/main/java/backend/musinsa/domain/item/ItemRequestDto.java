package backend.musinsa.domain.item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ItemRequestDto {

    private String name;
    private String category;
    private String brand;
    private String tag;
    private Integer price;
    private String status;
    private String itemNumber;
    private String imageUrl;
    private String thumbnailImageUrl;
    private List<String> colorOption;
    private List<String> sizeOption;
    private String gender;



}
