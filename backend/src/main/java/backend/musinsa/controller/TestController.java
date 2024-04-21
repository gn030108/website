package backend.musinsa.controller;


import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.member.MemberRequestDto;
import backend.musinsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;

    @PostMapping("test/register")
    public ResponseEntity<String> testRegister(@RequestBody MemberRequestDto memberRequestDto){
        log.info("memberId = "+memberRequestDto.getMemberId());
        log.info("password = "+memberRequestDto.getPassword());
        memberService.register(memberRequestDto);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("test/login")
    public TokenDto testLogin(@RequestBody MemberRequestDto memberRequestDto){
        log.info("memberId = "+memberRequestDto.getMemberId());
        log.info("password = "+memberRequestDto.getPassword());
        return memberService.signIn(memberRequestDto);
    }
    @PostMapping("test/member")
    public String testMember(@RequestBody MemberRequestDto memberRequestDto){
        return "인증 성공!";
    }




}
