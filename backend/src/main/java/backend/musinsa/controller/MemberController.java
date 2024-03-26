package backend.musinsa.controller;


import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.member.MemberRequestDto;
import backend.musinsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("Member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("login")
    private ResponseEntity<ApiResult> normalLogin(@RequestBody MemberRequestDto input){

        return memberService.login(input);

    }

    @PostMapping("register")
    private ResponseEntity<ApiResult> register(@RequestBody MemberRequestDto input){

        return memberService.register(input);

    }



}
