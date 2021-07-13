package com.example.PS13223_TranNgocPhu_ASM.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) { // chưa đăng nhập
           response.sendRedirect(request.getContextPath()+"/home/account/login");
           return false;
        }

        return true;
    }

}
