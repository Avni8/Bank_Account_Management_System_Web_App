/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.security;

import com.ams.controller.UserBean;
import com.ams.model.UserRole;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avni
 */
public class LoginCheckListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Inject
    UserBean userBean;

    private Boolean isClientLoggedIn() {
        return null != userBean.getCurrentClient();
    }

    private Boolean isStaffLoggedIn() {
        return null != userBean.getCurrentStaff();
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext facesContext = event.getFacesContext();

        String currentPage = facesContext.getViewRoot().getViewId();

        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();

        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

        if (!isLoginPage && !isClientLoggedIn() && !isStaffLoggedIn()) {

            nh.handleNavigation(facesContext, null,
                    "/login.xhtml?faces-redirect=true");
        } else if (isClientLoggedIn() || isStaffLoggedIn() || !isLoginPage) {

            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            UserRole userRole = (UserRole) httpServletRequest.getSession().getAttribute("userRole");

            if (currentPage.startsWith("/client/") && !userRole.equals(UserRole.CLIENT)) {
                nh.handleNavigation(facesContext, null,
                        "/accessDenied.xhtml");
            } else if (currentPage.startsWith("/staff/") && !userRole.equals(UserRole.STAFF)) {
                nh.handleNavigation(facesContext, null,
                        "/accessDenied.xhtml");
            }

        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
