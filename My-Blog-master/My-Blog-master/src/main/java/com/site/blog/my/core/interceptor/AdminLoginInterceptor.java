package com.site.blog.my.core.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();

        if (uri.startsWith("/") && null == request.getSession().getAttribute("loginUser")) {
            request.getSession().setAttribute("errorMsg", "没有访问权限");
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        } else {
            if (uri.startsWith("/admin") && "admin" != request.getSession().getAttribute("loginType")){
                request.getSession().setAttribute("errorMsg", "没有访问权限");
                response.sendRedirect(request.getContextPath() + "/index");
                return false;
            }
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
