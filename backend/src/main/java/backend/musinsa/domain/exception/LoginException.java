package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{
    private final ExceptionEnum error;
    public LoginException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
