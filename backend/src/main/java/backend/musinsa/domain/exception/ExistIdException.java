package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class ExistIdException extends RuntimeException{
    private final ExceptionEnum error;

    public ExistIdException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
