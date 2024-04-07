package backend.musinsa.domain.board;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardDto {

    private Long id;
    private String title;
    private String comment;
    private String brand;
    private String itemNumber;
    private String gender;
    private Integer like;

    private Integer price;
    private List<String> imageUrl;
    private List<String> thumbnailImageUrl;


}
