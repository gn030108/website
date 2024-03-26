package backend.musinsa.service;

import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ExistIdException;
import backend.musinsa.domain.exception.LoginException;
import backend.musinsa.domain.member.*;
import backend.musinsa.repository.MemberInfoRepository;
import backend.musinsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    public ResponseEntity<ApiResult> register(MemberRequestDto input){

        if(memberRepository.findByMemberId(input.getMemberId()).isPresent()){
            throw new ExistIdException(ExceptionEnum.ALREADY_EXIST_MEMBER_ID_EXCEPTION);
        }

        Member newMember = Member.builder()
                .memberId(input.getMemberId())
                .password(input.getPassword())
                .name(input.getName())
                .memberInfo(
                        MemberInfo.builder()
                                .age(input.getAge())
                                .email(input.getEmail())
                                .phoneNumber(input.getPhoneNumber())
                                .gender(input.getGender())
                                .address(input.getAddress())
                                .build()
                ).build();

        newMember.setRole(Role.ROLE_MEMBER);
        Member savedMember = memberRepository.save(newMember);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResult.builder()
                        .status("Register success")
                        .message("회원가입이 완료되었습니다!")
                        .build());
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
    public void tokenLogin(){

    }




}
