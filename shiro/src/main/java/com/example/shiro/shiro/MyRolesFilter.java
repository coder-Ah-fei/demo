package com.example.shiro.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyRolesFilter extends RolesAuthorizationFilter {
 
	
 
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws IOException {
 		System.out.println("执行了onAccessDenied方法");
		Subject subject = getSubject(request, response);
		// If the subject isn't identified, redirect to login URL
//		if (subject.getPrincipal() == null) {
//			saveRequestAndRedirectToLogin(request, response);
//		} else {
			// If subject is known but not authorized, redirect to the
			// unauthorized URL if there is one
			// If no unauthorized URL is specified, just return an unauthorized
			// HTTP status code
//			String unauthorizedUrl = getUnauthorizedUrl();
//			String ajaxHeader = ((HttpServletRequest)request).getHeader("X-Requested-With");
//			if (ajaxHeader != null && "XMLHttpRequest".equals(ajaxHeader)) {
				response.reset();
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("{\"msg\":\"没有通过roles认证\"}");
				response.flushBuffer();
//			}
			
			// SHIRO-142 - ensure that redirect _or_ error code occurs - both
			// cannot happen due to response commit:
//			else if (StringUtils.hasText(unauthorizedUrl)) {
//				WebUtils.issueRedirect(request, response, unauthorizedUrl);
//			}
 
//			else {
//				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//			}
//		}
		return false;
	}
 
}