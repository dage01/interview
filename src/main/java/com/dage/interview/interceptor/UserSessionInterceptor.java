package com.dage.interview.interceptor;


import com.dage.interview.dto.LoginDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        LoginDTO loginSession = (LoginDTO) session.getAttribute("loginDto");
        if (loginSession != null) {
            request.setAttribute("loginDto", loginSession);
        }
        return true;
    }
}
