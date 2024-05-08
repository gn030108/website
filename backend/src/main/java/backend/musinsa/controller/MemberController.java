package backend.musinsa.controller;


import backend.musinsa.domain.admin.AdminDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.member.MemberRequestDto;
import backend.musinsa.service.AdminService;
import backend.musinsa.service.ManagerLogoutService;
import backend.musinsa.service.ManagerReissueTokenService;
import backend.musinsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ManagerReissueTokenService managerReissueTokenService;
    private final ManagerLogoutService managerLogoutService;
    private final AdminService adminService;

    @PostMapping("login")
    private TokenDto normalLogin(@RequestBody MemberRequestDto input){
        log.info("memberId = "+input.getMemberId());
        log.info("password = "+input.getPassword());
        return memberService.signIn(input);
    }
    @PostMapping("register/admin")
    private ResponseEntity<ApiResult> adminRegister(@RequestBody AdminDto adminDto){
        return adminService.adminRegister(adminDto);
    }
    @PostMapping("register")
    private ResponseEntity<ApiResult> register(@RequestBody MemberRequestDto input){
        return memberService.register(input);
    }

    @PostMapping("logout")
    private void logout(
            @RequestHeader("Refresh") String refreshToken,
            @RequestBody MemberRequestDto memberRequestDto){
        log.info("로그아웃 요청이 들어옴. => "+memberRequestDto.getMemberId());
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
