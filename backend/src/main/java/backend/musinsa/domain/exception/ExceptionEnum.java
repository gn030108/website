package backend.musinsa.domain.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST,"E0001","잘못된 요청입니다."),
    BAD_LOGIN_TRY_EXCEPTION(HttpStatus.BAD_REQUEST,"E0002","존재하지 않는 회원입니다."),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED,"E0003","인증되지않은 요청입니다."),
    SECURITY_NOT_LOGIN_EXCEPTION(HttpStatus.UNAUTHORIZED,"S0001","로그인이 필요합니다."),
    ALREADY_EXIST_MEMBER_ID_EXCEPTION(HttpStatus.BAD_REQUEST,"S0002","이미 존재하는 회원 아이디 입니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}