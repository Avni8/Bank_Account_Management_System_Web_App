/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.service;

import com.ams.controller.UserBean;
import com.ams.model.Staff;
import com.ams.repository.StaffRepository;
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
    StaffRepository staffRepository;
    
    @Inject
    UserBean userBean;
    
    public Staff login(String username, String password){
        
        
        Staff staff = staffRepository.findByUsername(username);
        Staff loginStaff = null;

        if (staff != null) {

            if (BCrypt.checkpw(password, staff.getPassword())) {

                userBean.setCurrentStaff(staff);

                loginStaff = staff;
            } 
        }
        return loginStaff;
    }
}
    
    

