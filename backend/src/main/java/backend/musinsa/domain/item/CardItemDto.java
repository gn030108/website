package backend.musinsa.domain.item;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardItemDto {

    private Long id;
    private String name;
    private String brand;
    private Integer price;
    private Integer likes;
    private Integer stars;

    public static CardItemDto toDto(Item item){
        return CardItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .brand(item.getBrand())
                .price(item.getItemInfo().getPrice())
                .build();
    }

}
