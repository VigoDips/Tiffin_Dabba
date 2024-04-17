package com.kitchen.Tiffin.services;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var role = authorities.stream().map(r -> r.getAuthority()).findFirst().orElse("");

        if (role.equals("ADMIN")) {
            response.sendRedirect("/adminpage");
        } else if (role.equals("USER")) {
            response.sendRedirect("/userpage");
        } else {
            response.sendRedirect("/error");
        }
    }
}
