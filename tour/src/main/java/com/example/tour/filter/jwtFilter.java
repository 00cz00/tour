package com.example.tour.filter;

import com.example.tour.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;

import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;
import java.io.IOException;
import java.security.URIParameter;

@WebFilter(urlPatterns = "/*")
public class jwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        String url=req.getRequestURL().toString();
        System.out.println(url);


        if(url.contains("/userById")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String jwt = req.getHeader("jwt");
        if(!StringUtils.hasLength(jwt)){
           String notLogin="未登录";
           System.out.println(notLogin);
           ((HttpServletResponse) servletResponse).sendError(401);
            return;
        }
        try {
            Claims claims = JwtUtils.parserJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            String notLogin="未登录";
            System.out.println(notLogin);
            ((HttpServletResponse) servletResponse).sendError(401);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
