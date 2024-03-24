package backend.musinsa.domain.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String memberId;
    private String name;

    @Builder
    public MemberResponseDto(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
