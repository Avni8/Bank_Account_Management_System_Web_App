/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Staff;
import com.ams.repository.StaffRepository;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("staffLoginController")
public class StaffLoginController extends AbstractMessageController {
    
    private String username;
    private String password;

    @Inject
    private StaffRepository staffRepository;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        
        Staff staff = staffRepository.findByUsername(username);
        
        if (staff != null) {
            
            if (BCrypt.checkpw(password, staff.getPassword())) {
                
                return "home.xhtml?faces-redirect=true";
            } 
            else {
                
                errorMessage("Invalid Credentials. Try Again!");
            }
        } 
        else {
                errorMessage("Invalid Credentials. Try Again!");
        }
        
        return null;
    }
}
