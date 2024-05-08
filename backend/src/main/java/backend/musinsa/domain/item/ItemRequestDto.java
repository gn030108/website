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


}
