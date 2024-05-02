package backend.musinsa.service;


import backend.musinsa.domain.admin.Admin;
import backend.musinsa.domain.admin.AdminDto;
import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.ExceptionEnum;
import backend.musinsa.domain.exception.ExistIdException;
import backend.musinsa.domain.jwt.TokenProvider;
import backend.musinsa.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;

    public ResponseEntity<ApiResult> adminRegister(AdminDto adminDto){
        // 판매자
        if(adminRepository.existsByMemberId(adminDto.getMemberId())) {
            Admin admin = Admin.builder()
                    .memberId(adminDto.getMemberId())
                    .password(adminDto.getPassword())
                    .name(adminDto.getName())
                    .brand(adminDto.getBrand())
                    .roles(List.of("ADMIN"))
                    .build();
            adminRepository.save(admin);
            return ResponseEntity.ok().body(ApiResult.builder().status("success").message("계정 생성 완료").build());
        }else{
            throw new ExistIdException(ExceptionEnum.ALREADY_EXIST_MEMBER_ID_EXCEPTION);
        }
    }
    //로그인, 로그아웃 은 MemberService 사용.
}
