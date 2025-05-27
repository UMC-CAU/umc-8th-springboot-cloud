package umc.spring.apiPayload.exception;

/**
 * 동일 자원(엔티티, 요청 등)이 중복됐을 때 던지는 예외.
 * → 전역 ExceptionAdvice(예: @RestControllerAdvice)에서 잡아서
 *    409 CONFLICT 또는 400 BAD_REQUEST 등에 매핑하면 됩니다.
 */
public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
