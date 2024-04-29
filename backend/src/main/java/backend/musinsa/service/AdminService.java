package backend.musinsa.service;


import backend.musinsa.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {

    private AdminRepository adminRepository;

    public void adminRegister(){

    }

    public void adminLogin(){

    }

    public void adminLogout(){

    }


}
