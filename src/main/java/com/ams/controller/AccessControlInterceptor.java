/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author avni
 */
@Interceptor
public class AccessControlInterceptor{

//    @AroundInvoke
//    public Object checkAccess(InvocationContext context) throws Exception {
//        String requiredRole = context.getMethod().getAnnotation(RequiredPermission.class).value();
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
//                            .getExternalContext().getRequest();
//        
//        String userRole = (String) httpServletRequest.getSession().getAttribute("userRole");
//
//        if (userRole.equals(requiredRole)) {
//            return context.proceed();
//        } else {
//            throw new Exception("Access denied");
//        }
//    }

}
