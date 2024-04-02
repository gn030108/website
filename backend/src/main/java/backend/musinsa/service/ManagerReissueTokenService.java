package backend.musinsa.service;


import backend.musinsa.domain.exception.RefreshTokenValidator;
import backend.musinsa.domain.jwt.TokenDto;
import backend.musinsa.domain.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ManagerReissueTokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenValidator refreshTokenValidator;

    public TokenDto reissueToken(String refreshToken){
        refreshTokenValidator.validateToken(refreshToken);
        refreshTokenValidator.validateLogoutToken(refreshToken);
        return tokenProvider.reissueToken(refreshToken);
    }




}
