package com.example.wlplatform.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;



public class NetInterceptor implements HandlerInterceptor{
    private static final Logger LOGGER = LoggerFactory.getLogger(NetInterceptor.class);
    
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
    	LOGGER.info("preHandle begin:"+"uri: ["+httpServletRequest.getRequestURI()+"]\n"+
    				"url: ["+httpServletRequest.getRequestURL().toString()+"]\n"+
    				"para: ["+httpServletRequest.getQueryString()+"]\n"+
    				"srcIp: ["+httpServletRequest+"]\n"+
    				"srcHostName: ["+httpServletRequest.getRemoteHost()+"]\n"+
    				"srcUser: ["+httpServletRequest.getRemoteUser()+"]\n"+
    				"requestMethod: ["+httpServletRequest.getMethod()+"]\n"+
    				"requestPath: ["+httpServletRequest.getPathInfo()+"]\n"+
    				"requestParameter: ["+httpServletRequest.getParameter("UserInfo")+"]\n"
    			);
    	
       
        
        return true;

    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    	LOGGER.info("postHandle begin:"+"uri: ["+httpServletRequest.getRequestURI()+"]\n"+
//				"url: ["+httpServletRequest.getRequestURL().toString()+"]\n"+
//				"para: ["+httpServletRequest.getQueryString()+"]\n"+
//				"srcIp: ["+httpServletRequest+"]\n"+
//				"srcHostName: ["+httpServletRequest.getRemoteHost()+"]\n"+
//				"srcUser: ["+httpServletRequest.getRemoteUser()+"]\n"+
//				"requestMethod: ["+httpServletRequest.getMethod()+"]\n"+
//				"requestPath: ["+httpServletRequest.getPathInfo()+"]\n"
//    			);
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    	LOGGER.info("afterCompletion begin:"+"uri: ["+httpServletRequest.getRequestURI()+"]\n"+
//				"url: ["+httpServletRequest.getRequestURL().toString()+"]\n"+
//				"para: ["+httpServletRequest.getQueryString()+"]\n"+
//				"srcIp: ["+httpServletRequest+"]\n"+
//				"srcHostName: ["+httpServletRequest.getRemoteHost()+"]\n"+
//				"srcUser: ["+httpServletRequest.getRemoteUser()+"]\n"+
//				"requestMethod: ["+httpServletRequest.getMethod()+"]\n"+
//				"requestPath: ["+httpServletRequest.getPathInfo()+"]\n"
//    			);
    }

   
}