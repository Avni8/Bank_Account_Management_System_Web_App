/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.User;
import com.ams.model.UserRole;
import com.ams.repository.AbstractRepository;
import com.ams.repository.UserRepository;
import com.ams.repository.UserRoleRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("userController")
public class UserController extends AbstractController {

    private User user;
    private List<User> userList;
    private List<UserRole> roleList;

    @Inject
    UserRepository userRepository;

    @Inject
    UserRoleRepository roleRepository;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @PostConstruct
    public void init() {
        loadData();
    }

    public List<UserRole> getRoleList() {
        if (roleList == null) {
            roleList = roleRepository.findAll();
        }
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public void beforeCreate() {
        user = new User();
    }

    public void beforeUpdate(User user) {
        this.user = user;
    }

    @Override
    public void loadData() {

        userList = userRepository.findAll();

    }

//    public void createUserAccount() {
//
//        if (!isUsernameTaken()) {
//            super.createUpdate();
//            }
//            //else {
////            super.warningMessage("Username already taken");
////
////        }
//
//    }

    public boolean isUsernameTaken() {

        User accHolder = new User();
        accHolder = userRepository.findByUsername(user.getUsername());
        if (accHolder != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AbstractRepository getRepository() {
        return userRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return user;
    }

}
