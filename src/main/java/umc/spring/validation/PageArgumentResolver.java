// src/main/java/umc/spring/validation/PageArgumentResolver.java
package umc.spring.validation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.validation.annotation.PageValid;

/**
 * <pre>
 *  ❑ page 쿼리스트링 규칙
 *   · 1 이상의 정수만 허용
 *   · 내부적으로는 0-base 로 변환해 컨트롤러 파라미터에 주입
 *
 *  ❑ 예외 처리
 *   · 음수·0 또는 숫자 변환 실패 → GeneralException(ErrorStatus._BAD_REQUEST)
 *   · 전역 ExceptionAdvice 에서 공통 JSON 응답 반환
 * </pre>
 */
@Component
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageValid.class)
                && Integer.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter param,
                                  ModelAndViewContainer mav,
                                  NativeWebRequest req,
                                  WebDataBinderFactory binder) {

        String raw = req.getParameter("page");

        int page;
        try {
            page = (raw == null) ? 1 : Integer.parseInt(raw);
        } catch (NumberFormatException ex) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        if (page < 1)
            throw new GeneralException(ErrorStatus._BAD_REQUEST);

        /* 0-based 인덱스로 변환 */
        return page - 1;
    }
}
