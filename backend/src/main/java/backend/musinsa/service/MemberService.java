package backend.musinsa.service;

import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ExistIdException;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.jwt.TokenProvider;
import backend.musinsa.domain.member.*;
import backend.musinsa.repository.MemberInfoRepository;
import backend.musinsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResult> register(MemberRequestDto input){
        
        if(memberRepository.existsByMemberId(input.getMemberId())){
            log.info("예외발생");
            throw new ExistIdException(ExceptionEnum.ALREADY_EXIST_MEMBER_ID_EXCEPTION);
        }
        Member newMember = Member.builder()
                .memberId(input.getMemberId())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .memberInfo(memberInfoSave(input))
                .build();
        newMember.setRoles(List.of("MEMBER"));

        Member savedMember = memberRepository.save(newMember);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResult.builder()
                        .status("Register success")
                        .message("회원가입이 완료되었습니다!")
                        .build());
    }

    private MemberInfo memberInfoSave(MemberRequestDto input) {
        MemberInfo memberInfo = MemberInfo.builder()
                .age(input.getAge())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .gender(input.getGender())
                .address(input.getAddress())
                .build();
        return memberInfoRepository.save(memberInfo);
    }

    public TokenDto signIn(MemberRequestDto input){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(input.getMemberId(), input.getPassword());

        // 실제 인증
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateToken(authentication);

    }

    /**
     * member Info 정보 수정
     * @param memberRequestDto
     * @return
     */
    public ResponseEntity<ApiResult> changeInfo(MemberRequestDto memberRequestDto) {

        //Access token을 사용자 memberId 조회
        String username = SecurityUtil.getCurrentUsername();
        //이후 사용자의 정보변경.
        Member member = memberRepository.findByMemberId(username).orElseThrow();
        MemberInfo memberInfo = member.getMemberInfo();
        memberInfo.changeInfo(memberRequestDto);

        return ResponseEntity.ok(new ApiResult("success","정상적으로 변경"));

    }
    //비밀번호 변경
}
