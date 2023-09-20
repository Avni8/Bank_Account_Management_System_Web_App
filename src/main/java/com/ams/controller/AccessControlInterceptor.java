/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.ActionType;
import com.ams.model.ResourceType;
import com.ams.model.UserRole;
import com.ams.repository.AccessControlRepository;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avni
 */
@RequiredPermission
@Interceptor
@Dependent
@Priority(Interceptor.Priority.APPLICATION)
public class AccessControlInterceptor extends AbstractMessageController {

    @Inject
    AccessControlRepository accessControlRepository;

    @AroundInvoke
    public Object checkAccess(InvocationContext context) throws Exception {

//        Class<?> targetClass = context.getTarget().getClass();
//        PagePermission pagePermission = targetClass.getAnnotation(PagePermission.class);
//
//        if (pagePermission != null && !hasPermission(pagePermission)) {
//            redirectToAccessDeniedPage();
//            return null;
//        }

        boolean isAllowed = false;
        Method m = context.getMethod();
        RequiredPermission methodName = m.getAnnotation(RequiredPermission.class);

        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        HttpSession session = httpServletRequest.getSession();
        UserRole userRole = (UserRole) session.getAttribute("userRole");

        if (methodName != null) {

            ActionType retrievedAction = methodName.action();
            ResourceType retrievedResource = methodName.resource();

            isAllowed = accessControlRepository.
                    isPermissionAllowed(userRole, retrievedResource, retrievedAction);

        }

        if (isAllowed) {
            return context.proceed();
        } else {

            throw new SecurityException("Access denied");
        }

    }

//    private boolean hasPermission(PagePermission permission) {
//        
//        String requiredRole = permission.value();
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
//                .getExternalContext().getRequest();
//
//        HttpSession session = httpServletRequest.getSession();
//        UserRole userRole = (UserRole) session.getAttribute("userRole");
//        String role = userRole.toString();
//
//        if (role != null && role.equals(requiredRole)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private void redirectToAccessDeniedPage() throws IOException {
//
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        String contextPath = facesContext.getExternalContext().getRequestContextPath();
//        facesContext.getExternalContext().redirect(contextPath + "/accessDenied.xhtml");
//    }

}
