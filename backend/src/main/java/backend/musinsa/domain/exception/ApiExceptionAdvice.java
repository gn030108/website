package backend.musinsa.domain.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<ApiResult> loginExceptionHandler(LoginException e){

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.BAD_LOGIN_TRY_EXCEPTION.getCode())
                .errorMessage(e.getMessage())
                .build();

        e.printStackTrace();

        return ResponseEntity
                .status(ExceptionEnum.BAD_LOGIN_TRY_EXCEPTION.getStatus())
                .body(ApiResult.builder()
                        .status("error")
                        .message("아이디 혹은 비밀번호가 다릅니다.")
                        .exception(apiExceptionEntity)
                        .build());
    }
}
