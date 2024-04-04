package backend.musinsa.domain.exception;

import lombok.Getter;

@Getter
public class ItemException extends RuntimeException{
    private final ExceptionEnum error;
    public ItemException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
