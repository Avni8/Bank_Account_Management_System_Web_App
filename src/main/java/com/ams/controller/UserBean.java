/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Staff;
import com.ams.model.Client;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Staff currentStaff;
    private Client currentClient;

    public UserBean() {

    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }
}
