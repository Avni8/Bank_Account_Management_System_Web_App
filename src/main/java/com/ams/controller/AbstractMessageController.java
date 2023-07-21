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

    }

}
