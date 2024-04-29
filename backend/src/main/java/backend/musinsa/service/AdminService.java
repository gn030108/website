package backend.musinsa.service;


import backend.musinsa.domain.jwt.TokenProvider;
import backend.musinsa.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public void adminRegister(){

    }

    public void adminLogin(){

    }

    public void adminLogout(){

    }


}
