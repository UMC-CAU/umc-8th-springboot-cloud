// src/main/java/umc/spring/validation/PageArgumentResolver.java
package umc.spring.validation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.validation.annotation.PageValid;

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
        int page = (raw == null) ? 1 : Integer.parseInt(raw);

        if (page < 1) throw new IllegalArgumentException("page 파라미터는 1 이상이어야 합니다.");
        return page - 1;                     // 0-base 인덱스로 변환
    }
}
