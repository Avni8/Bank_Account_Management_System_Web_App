/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.Staff;
import com.ams.model.User;
import com.ams.repository.AccountRepository;
import com.ams.repository.StaffRepository;
import com.ams.repository.UserRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<Account> userAccounts;

    @Inject
    private StaffRepository staffRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private SessionController sessionController;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserBean userBean;

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

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public String staffLogin() {

        Staff staff = staffRepository.findByUsername(username);

        if (staff != null) {

            if (BCrypt.checkpw(password, staff.getPassword())) {

                HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();

                httpServletRequest.getSession().setAttribute("loggedInStaff", staff);
//                sessionController.setLoggedInUserName(staff.getUsername());

                userBean.setCurrentStaff(staff);

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

//                sessionController.setLoggedInUserName(user.getName());
                
                userBean.setCurrentUser(user);

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
