package com.example.shiro.shiro;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

class MyLoginFilter extends AdviceFilter {

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("执行了preHandle方法");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        SysUser sysUser = (SysUser) httpServletRequest.getSession().getAttribute("user");
//        if (null == sysUser && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
//            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
//            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
//                ResponseHeader responseHeader = new ResponseHeader();
//                responseHeader.setResponse(ResponseHeader.SC_MOVED_TEMPORARILY, null);
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.setContentType("application/json");
//                httpServletResponse.getWriter().write(JSONObject.toJSONString(responseHeader));
//                return false;
//            } else {//不是ajax进行重定向处理
//                httpServletResponse.sendRedirect("/login/local");
//                return false;
//            }
//        }
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{\"msg\":\"没有登录\"}");
        response.flushBuffer();

        return true;
    }

}