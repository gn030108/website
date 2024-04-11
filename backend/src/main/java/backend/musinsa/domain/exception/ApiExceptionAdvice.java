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

    @ExceptionHandler({ExistIdException.class})
    public ResponseEntity<ApiResult> existIdExceptionHandler(ExistIdException e){

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.ALREADY_EXIST_MEMBER_ID_EXCEPTION.getCode())
                .errorMessage(e.getMessage())
                .build();

        e.printStackTrace();

        return ResponseEntity
                .status(ExceptionEnum.ALREADY_EXIST_MEMBER_ID_EXCEPTION.getStatus())
                .body(ApiResult
                        .builder()
                        .status("error")
                        .message("이미 존재하는 아이디 입니다.")
                        .exception(apiExceptionEntity)
                        .build());

    }
    @ExceptionHandler({TokenWithOutCredentials.class})
    public ResponseEntity<ApiResult> tokenWithOutCredentials(TokenWithOutCredentials e){

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS.getCode())
                .errorMessage(e.getMessage())
                .build();

        e.printStackTrace();

        return ResponseEntity
                .status(ExceptionEnum.TOKEN_WITH_OUT_CREDENTIALS.getStatus())
                .body(ApiResult
                        .builder()
                        .status("error")
                        .message(e.getMessage())
                        .exception(apiExceptionEntity)
                        .build());
    }

    @ExceptionHandler({ItemException.class})
    public ResponseEntity<ApiResult> itemException(ItemException e){

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.ITEM_REGISTRATION_FAIL.getCode())
                .errorMessage(e.getMessage())
                .build();

        e.printStackTrace();

        return ResponseEntity
                .status(ExceptionEnum.ITEM_REGISTRATION_FAIL.getStatus())
                .body(ApiResult
                        .builder()
                        .status("error")
                        .message(ExceptionEnum.ITEM_REGISTRATION_FAIL.getMessage())
                        .exception(apiExceptionEntity)
                        .build());
    }
    @ExceptionHandler({NotFoundInformationException.class})
    public ResponseEntity<ApiResult> notFoundInformationException(NotFoundInformationException e){

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION.getCode())
                .errorMessage(e.getMessage())
                .build();

        e.printStackTrace();

        return ResponseEntity
                .status(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION.getStatus())
                .body(ApiResult
                        .builder()
                        .status("error")
                        .message(ExceptionEnum.NOT_FOUND_ORDER_INFORMATION.getMessage())
                        .exception(apiExceptionEntity)
                        .build());
    }



}
