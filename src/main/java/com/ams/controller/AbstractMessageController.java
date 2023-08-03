/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author avni
 */
public abstract class AbstractMessageController implements Serializable {

    public void addMessage(FacesMessage.Severity severity, String summary) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoMessage(String message) {

        addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    public void warningMessage(String message) {
         addMessage(FacesMessage.SEVERITY_WARN, message);
    }
    
    public void errorMessage(String message) {
         addMessage(FacesMessage.SEVERITY_ERROR, message);
    }
    
    public void showMultiple(String message1, String message2, String message3) {
        addMessage(FacesMessage.SEVERITY_INFO, message1);
        addMessage(FacesMessage.SEVERITY_INFO, message2);
        addMessage(FacesMessage.SEVERITY_INFO, message3);
    }

}
