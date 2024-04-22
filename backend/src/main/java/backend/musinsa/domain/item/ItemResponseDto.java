package backend.musinsa.domain.item;


import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;
    private String name;
    private List<String> category;
    private String brand;
    private List<String> tag;
    private Integer price;
    private String status;
    private List<String> colorOption;
    private List<String> sizeOption;
    private String gender;
    @Builder
    public ItemResponseDto(String name, List<String> category, String brand, List<String> tag, Integer price, String status, List<String> colorOption, List<String> sizeOption, String gender) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.tag = tag;
        this.price = price;
        this.status = status;
        this.colorOption = colorOption;
        this.sizeOption = sizeOption;
        this.gender = gender;
    }

    @QueryProjection
    public ItemResponseDto(Long id, String name, List<String> category, String brand, List<String> tag, Integer price, String status, List<String> colorOption, List<String> sizeOption, String gender) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.tag = tag;
        this.price = price;
        this.status = status;
        this.colorOption = colorOption;
        this.sizeOption = sizeOption;
        this.gender = gender;
    }
}
