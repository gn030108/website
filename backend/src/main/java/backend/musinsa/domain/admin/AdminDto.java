package backend.musinsa.domain.admin;


import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminDto {

    private Long id;

    private String memberId;

    private String password;

    private String name;

    private String brand;


}
