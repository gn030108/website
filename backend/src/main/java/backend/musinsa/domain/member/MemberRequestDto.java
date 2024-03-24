package backend.musinsa.domain.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private String memberId;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer age;
    private String gender;

    @Builder
    public MemberRequestDto(String memberId, String password, String name,
                            String address, String phoneNumber, Integer age, String gender,String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }
}
