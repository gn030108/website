package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class TokenWithOutCredentials extends RuntimeException{

    private final ExceptionEnum error;

    public TokenWithOutCredentials(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }
}
