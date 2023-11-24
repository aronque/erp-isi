package com.system.backend.config;

import com.system.backend.services.ControleAcessoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class ControleAcessoInterceptor implements HandlerInterceptor {



//    @Autowired
//    ControleAcessoService caService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        caService.temPersmissao(Long.valueOf(request.getHeader("userId")), request.getRequestURI());
//    }
//
//    private String parseURL(StringBuilder uri) {
//        String endpoint = uri.toString().replace("localhost:8080/", "");
//    }
}
