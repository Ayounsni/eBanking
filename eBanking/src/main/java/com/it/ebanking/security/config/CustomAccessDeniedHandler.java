package com.it.ebanking.security.config;

import com.it.ebanking.models.dtos.Error.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        try {
            ErrorDTO errorDTO = new ErrorDTO("Accès refu : " + accessDeniedException.getMessage(), HttpStatus.FORBIDDEN.value());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write(errorDTO.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

