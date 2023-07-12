/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.repository.UserRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.User;
import java.util.ArrayList;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("userController")
public class UserController implements Serializable {

    private User user;
    private List<User> userList;

    @Inject
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        user = new User();
        loadData();
    }

    private void loadData() {
        userList = userRepository.findAll();
    }

    public void beforeCreate() {
        user = new User();
    }

    public void beforeUpdate(User user) {
        this.user = user;
    }

    public void createUpdate() {
        if (user.getId() == null) {
            userRepository.save(user);
        } else {
            userRepository.update(user);
        }
        loadData();
    }

    public void delete(User user) {
        userRepository.delete(user.getId());
        loadData();
    }
    
//    public void findUserById(){
//        this.userList =  userRepository.getUserById(user.getId());
//        System.out.println("");
//    }

}
