/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Staff;
import com.ams.model.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avni
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Exclude PrimeFaces resource URLs
        if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/javax.faces.resource/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        String loginPage = httpRequest.getContextPath() + "/login.xhtml";
        boolean isLoginPage = httpRequest.getRequestURI().equals(loginPage);

        
        Staff loggedInStaff = (Staff) (session != null ? session.getAttribute("loggedInStaff") 
                : null);
        User loggedInClient = (User) (session != null ? session.getAttribute("loggedInClient") 
                : null);

        if (loggedInStaff != null || loggedInClient != null || isLoginPage) {
            
            chain.doFilter(request, response);
        } else {
            
            httpResponse.sendRedirect(loginPage);
        }
        
    }

    @Override
    public void destroy() {
    }
}
