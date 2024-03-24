package backend.musinsa.service;

import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.member.MemberInfo;
import backend.musinsa.domain.member.MemberRequestDto;
import backend.musinsa.domain.member.MemberResponseDto;
import backend.musinsa.repository.MemberInfoRepository;
import backend.musinsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    public MemberResponseDto register(MemberRequestDto input){
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

        Member savedMember = memberRepository.save(newMember);

        return MemberResponseDto.builder()
                .memberId(savedMember.getMemberId())
                .name(savedMember.getName())
                .build();
    }


    public void login(MemberRequestDto input) throws LoginException {

        Member byMemberId = memberRepository.findByMemberId(input.getMemberId())
                .orElseThrow(LoginException::new);

        if(byMemberId.getPassword().equals(input.getPassword())){
            //로그인 성공시
        }

        //비밀번호가 다를시



    }
    public void tokenLogin(){

    }




}
