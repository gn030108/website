package backend.musinsa.domain.exception;


import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.jwt.TokenProvider;
import backend.musinsa.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenValidator {

    private final TokenProvider tokenProvider;
    private final BlackListRepository blackListRepository;

    public void validateToken(String refreshToken){
        if(!tokenProvider.validateToken(refreshToken)){
            throw new TokenWithOutCredentials(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS);
        }
    }

    public void validateTokenOwnerId(String refreshToken, String memberId){
        String username = tokenProvider.extractMemberId(refreshToken);
        if(!username.equals(memberId)){
            throw new TokenWithOutCredentials(ExceptionEnum.DIFFERENT_TOKEN_USER);
        }
    }
    public void validateLogoutToken(String refreshToken){
        if(blackListRepository.existsByInvalidRefreshToken(refreshToken)){
            throw new TokenWithOutCredentials(ExceptionEnum.ALREADY_LOGOUT_USER);
        }

    }


}
