package backend.musinsa.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewDto {

    private String orderItemId;

    private String reviewId;

    private String name;
    //부모님 사준거 애기 입힌거
    private String gender;
    private Integer height;
    private Integer weight;

    private List<String> itemUrl;
    private String itemName;
    private String itemColorOption;
    private String itemSizeOption;
    private String grade;
    private String evalSize;
    private String evalBright;
    private String evalColor;
    private String evalThickness;
    private String evalDelivery;
    private String evalPackaging;
    private String text;

}
