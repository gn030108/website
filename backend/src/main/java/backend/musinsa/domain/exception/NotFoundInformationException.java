package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundInformationException extends RuntimeException{

    private final ExceptionEnum error;
    public NotFoundInformationException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
