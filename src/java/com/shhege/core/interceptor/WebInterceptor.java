package com.shhege.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shhege.common.constant.CacheKeyConstant;

public class WebInterceptor extends HandlerInterceptorAdapter {
	
	@Override    
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getSession().getAttribute(CacheKeyConstant.login_user) == null) {
			request.getRequestDispatcher("/login.do").forward(request, response);
			return false;
		} else {
			return true;
		}
	}
	
	@Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}
	
	@Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {
		System.out.println("afterCompletion");
	}
}
