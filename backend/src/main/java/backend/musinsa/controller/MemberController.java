package backend.musinsa.controller;


import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.member.MemberRequestDto;
import backend.musinsa.service.ManagerLogoutService;
import backend.musinsa.service.ManagerReissueTokenService;
import backend.musinsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("Member")
public class MemberController {

    private final MemberService memberService;
    private final ManagerReissueTokenService managerReissueTokenService;
    private final ManagerLogoutService managerLogoutService;

    @PostMapping("login")
    private TokenDto normalLogin(@RequestBody MemberRequestDto input){

        return memberService.signIn(input);

    }

    @PostMapping("register")
    private ResponseEntity<ApiResult> register(@RequestBody MemberRequestDto input){

        return memberService.register(input);

    }

    @PostMapping("logout")
    private void logout(
            @RequestHeader("Refresh") String refreshToken,
            MemberRequestDto memberRequestDto){
        managerLogoutService.logout(memberRequestDto.getMemberId(),refreshToken);
    }

    @GetMapping("reissue-token")
    private ResponseEntity<TokenDto> reissueToken(@RequestHeader("Refresh") String refreshToken){
        return ResponseEntity.ok(managerReissueTokenService.reissueToken(refreshToken));
    }

    @PostMapping("change-info")
    private ResponseEntity<ApiResult> changeInfo(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.changeInfo(memberRequestDto);
    }

}
