package com.concertai.user.management.framework.web;

import com.concertai.user.entity.token.AccessDeniedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class AccessDeniedInterceptor extends ResponseEntityExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex.getCause() instanceof AccessDeniedException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return new ModelAndView();
        }
        return null;
    }
}
