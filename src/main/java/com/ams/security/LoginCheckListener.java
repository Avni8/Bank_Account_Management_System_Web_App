/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.security;

import com.ams.controller.UserBean;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
public class LoginCheckListener implements PhaseListener {

//    private static final long serialVersionUID = 1L;

    @Inject
    UserBean userBean;

    private Boolean isUserLoggedIn() {
        return null != userBean.getCurrentUser();
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

        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

        if (!isLoginPage && !isUserLoggedIn() && !isStaffLoggedIn()) {

            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();

            nh.handleNavigation(facesContext, null, "/login.xhtml?faces-redirect=true");
        }
    }
    
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
