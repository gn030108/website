package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class FailFavoriteItemAddException extends RuntimeException{
    private final ExceptionEnum error;
    public FailFavoriteItemAddException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
