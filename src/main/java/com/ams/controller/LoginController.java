/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Staff;
import com.ams.model.User;
import com.ams.repository.StaffRepository;
import com.ams.repository.UserRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author avni
 */
@Named("loginController")
@ViewScoped
public class LoginController extends AbstractMessageController {

    private String username;
    private String password;

    @Inject
    private StaffRepository staffRepository;

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private SessionController sessionController;

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

    public String staffLogin() {

        Staff staff = staffRepository.findByUsername(username);

        if (staff != null) {

            if (BCrypt.checkpw(password, staff.getPassword())) {

                HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();

                httpServletRequest.getSession().setAttribute("loggedInStaff", staff);
                sessionController.setLoggedInUserName(staff.getUsername());

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
                } catch (IOException e) {

                    e.printStackTrace();
                }

            } else {

                errorMessage("Invalid Credentials. Try Again!");
            }
        } else {
            errorMessage("Invalid Credentials. Try Again!");
        }

        return null;
    }

    public String clientLogin() {

        User user = userRepository.findByUsername(username);

        if (user != null) {

            if (BCrypt.checkpw(password, user.getPassword())) {

                HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();

                httpServletRequest.getSession().setAttribute("loggedInClient", user);
                sessionController.setLoggedInUserName(user.getName());
                

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("clientHome.xhtml");
                } catch (IOException e) {

                    e.printStackTrace();
                }

            } else {

                errorMessage("Invalid Credentials. Try Again!");
            }
        } else {
            errorMessage("Invalid Credentials. Try Again!");
        }
        return null;
    }

}
