package com.example.shiro.shiro;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

public class MyLogOutFilter extends LogoutFilter {


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        if (this.isPostOnlyLogout() && !WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {

            try {
                subject.logout();
            } catch (SessionException var6) {
            }

            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("{\"msg\":\"已经退出登录\"}");
            response.flushBuffer();
            return false;
        }



    }
}
