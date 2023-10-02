/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.ActionType;
import com.ams.model.ResourceType;
import com.ams.model.UserRole;
import com.ams.repository.AccessControlRepository;
import java.io.Serializable;
import java.lang.reflect.Method;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 *
 * @author avni
 */
@RequiredPermission
@Interceptor
@Dependent
@Priority(Interceptor.Priority.APPLICATION)
public class AccessControlInterceptor implements Serializable {

    @Inject
    AccessControlRepository accessControlRepository;

    @Inject
    @Context
    HttpServletRequest httpServletRequest;

    @Inject
    UserBean userBean;

    @AroundInvoke
    public Object checkAccess(InvocationContext context) throws Exception {

        boolean isAllowed = false;
        Method m = context.getMethod();

//        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
//                .getExternalContext().getRequest();
//        HttpSession session = httpServletRequest.getSession();
        if (httpServletRequest != null) {

            UserRole userRole = (UserRole) httpServletRequest.getSession().getAttribute("userRole");

            if (userRole != null) {

                RequiredPermission methodName = m.getAnnotation(RequiredPermission.class);

                if (methodName != null) {

                    ActionType retrievedAction = methodName.action();
                    ResourceType retrievedResource = methodName.resource();

                    isAllowed = accessControlRepository.
                            isPermissionAllowed(userRole, retrievedResource, retrievedAction);
                }
            }
        }
        if (isAllowed) {
            return context.proceed();
        } else {

            throw new SecurityException("Access denied");
        }
    }
}
