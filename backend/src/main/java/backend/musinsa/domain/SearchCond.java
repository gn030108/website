package backend.musinsa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCond {

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
