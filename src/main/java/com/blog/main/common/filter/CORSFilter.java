package com.blog.main.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
@Component
@ServletComponentScan
public class CORSFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);
    private static final String REQUEST_OPTIONS = "OPTIONS";
//    @Value("${bee.allowOrigin:*}")
//    private String allowOrigin;
    private static final String allowOrigin = "*";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String orgHeader = request.getHeader(HttpHeaders.ORIGIN);
        if (StringUtils.isEmpty(orgHeader) || "null".equals(orgHeader))
            orgHeader = "localhost";
        if (!"*".equals(allowOrigin))
            orgHeader = allowOrigin;
        // 允许的跨域的域名
        response.addHeader("Access-Control-Allow-Origin", orgHeader);
//        response.addHeader("Access-Control-Allow-Origin", allowOrigin);
        // 允许携带 cookies 等认证信息
        response.addHeader("Access-Control-Allow-Credentials", "true");
//            // 允许跨域的方法
        response.addHeader("Access-Control-Allow-Methods", "*");
//
//
//            // 允许跨域请求携带的请求头
        response.addHeader("Access-Control-Allow-Headers", "Origin,Content-Type, Content-Length, Authorization, Accept, X-Requested-With,Cookies,User-Agent,X-Real-IP,X-Forwarded-For");
//            // 返回结果可以用于缓存的最长时间，单位是秒。-1表示禁用
//            response.addHeader("Access-Control-Max-Age", "360000");
//
//            response.addHeader("Access-Control-Expose-Headers","*");

        // 跨域预检请求，直接返回
        if (REQUEST_OPTIONS.equalsIgnoreCase(request.getMethod())) {
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

