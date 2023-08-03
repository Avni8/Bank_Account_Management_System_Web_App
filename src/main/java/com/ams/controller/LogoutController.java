/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avni
 */
@Named("logoutController")
@RequestScoped
public class LogoutController extends AbstractMessageController {

    public void logout() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        try {
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}

