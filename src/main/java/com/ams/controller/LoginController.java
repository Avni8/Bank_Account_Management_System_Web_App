/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.Staff;
import com.ams.service.LoginService;
import com.ams.model.Client;
import com.ams.model.User;
import com.ams.model.UserRole;
import com.ams.repository.AccountRepository;
import com.ams.repository.StaffRepository;
import com.ams.repository.ClientRepository;
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

    @Inject
    private StaffRepository staffRepository;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private SessionController sessionController;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserBean userBean;

    @Inject
    private LoginService loginService;

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

    public void login() {

        User returnedUser = loginService.login(username, password);

        if (returnedUser != null) {
            
            UserRole userRole = returnedUser.getRole();
            
            if (userRole == UserRole.CLIENT) {
                
                Client client = clientRepository.getClientByUser(returnedUser);

                if (client != null) {
                    HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                            .getExternalContext().getRequest();
                    httpServletRequest.getSession().setAttribute("userRole", userRole);
                    userBean.setCurrentClient(client);
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/project/client/clientHome.xhtml");
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            } else if (userRole == UserRole.STAFF) {
                
                Staff staff = staffRepository.getStaffByUser(returnedUser);

                if (staff != null) {
                    HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                            .getExternalContext().getRequest();
                    httpServletRequest.getSession().setAttribute("userRole", userRole);
                    userBean.setCurrentStaff(staff);
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/project/staff/home.xhtml");
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
        errorMessage("Invalid Credentials. Try Again!");
    }
}
