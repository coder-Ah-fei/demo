package com.example.shiro.shiro;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyPermsFilter extends PermissionsAuthorizationFilter {
    /**
     * shiro认证perms资源失败后回调方法
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("执行了onAccessDenied方法");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
//        if (StringUtils.isNotEmpty(requestedWith) &&
//                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
//            ResponseHeader responseHeader = new ResponseHeader();
//            responseHeader.setResponse(ResponseHeader.SC_FORBIDDEN, null);
//            httpServletResponse.setCharacterEncoding("UTF-8");
//            httpServletResponse.setContentType("application/json");
//            httpServletResponse.getWriter().write(JSONObject.toJSONString(responseHeader));
//        } else {//如果是普通请求进行重定向
//            httpServletResponse.sendRedirect("/403");
//        }


        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{\"msg\":\"没有通过perms认证\"}");
        response.flushBuffer();


        return false;
    }
}