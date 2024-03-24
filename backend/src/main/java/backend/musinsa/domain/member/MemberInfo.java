package backend.musinsa.domain.member;


import backend.musinsa.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity
public class MemberInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String phoneNumber;
    private String email;
    private Integer age;
    private String gender;

    @OneToOne(mappedBy = "memberInfo")
    private Member member;

    @Builder
    public MemberInfo(String address, String phoneNumber, String email, Integer age, String gender) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}

