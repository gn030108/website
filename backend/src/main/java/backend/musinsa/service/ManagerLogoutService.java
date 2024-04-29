package backend.musinsa.service;


import backend.musinsa.domain.exception.ApiResult;
import backend.musinsa.domain.exception.RefreshTokenValidator;
import backend.musinsa.domain.jwt.BlackList;
import backend.musinsa.repository.BlackListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ManagerLogoutService {

        private final BlackListRepository blackListRepository;
        private final RefreshTokenValidator refreshTokenValidator;

        public void logout(String memberId, String refreshToken){

            refreshTokenValidator.validateToken(refreshToken);
            refreshTokenValidator.validateTokenOwnerId(refreshToken,memberId);
            refreshTokenValidator.validateLogoutToken(refreshToken);
            blackListRepository.save(new BlackList(refreshToken));
        }

}
