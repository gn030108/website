package backend.musinsa.service;

import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ExistIdException;
import backend.musinsa.domain.exception.LoginException;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.jwt.TokenProvider;
import backend.musinsa.domain.member.*;
import backend.musinsa.repository.MemberInfoRepository;
import backend.musinsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

    public ResponseEntity<ApiResult> login(MemberRequestDto input){

        try {
            Member byMemberId = memberRepository.findByMemberId(input.getMemberId())
                    .orElseThrow();

            if(byMemberId.getPassword().equals(input.getPassword())){
                //로그인 성공시
                return ResponseEntity.status(HttpStatus.OK)
                        .body(ApiResult.builder()
                                .status("Login success")
                                .message("로그인 성공")
                                .build());
            }
            //비밀번호가 다를시
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResult.builder()
                            .status("Login fail")
                            .message("아이디 혹은 비밀번호가 다릅니다.")
                            .build());
        }catch (NoSuchElementException e){
            throw new LoginException(ExceptionEnum.BAD_LOGIN_TRY_EXCEPTION);
        }
    }

    public TokenDto signIn(MemberRequestDto input){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(input.getMemberId(), input.getPassword());

        // 실제 인증
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateToken(authentication);

    }


}
