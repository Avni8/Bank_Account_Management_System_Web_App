/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.service;

import com.ams.controller.UserBean;
import com.ams.model.Staff;
import com.ams.model.User;
import com.ams.repository.StaffRepository;
import com.ams.repository.UserRepository;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author avni
 */
@Stateless
public class LoginService {
    
    @Inject
    UserRepository userRepository;
    
    public User login(String username, String password){
        
        
        User user = userRepository.findByUsername(username);
        User loginUser = null;

        if (user != null) {

            if (BCrypt.checkpw(password, user.getPassword())) {

//                userBean.setCurrentUser(user);

                loginUser = user;
            } 
        }
        return loginUser;
    }
}
    
    

