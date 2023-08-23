/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.ActionType;
import com.ams.model.ResourceType;
import com.ams.model.User;
import com.ams.model.UserRole;
import com.ams.repository.AbstractRepository;
import com.ams.repository.UserRepository;
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

    @Inject
    UserRepository userRepository;

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
        user = new User();
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

    public UserRole[] getUserRole() {
        return UserRole.values();
    }

    @Override
    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.USER)
    public void createUpdate() {
        if (getEntity().getId() == null && !isUsernameTaken()) {
            getRepository().save(getEntity());
            super.infoMessage("Created Successfully");
        } else if (getEntity().getId() == null && isUsernameTaken()) {
            super.warningMessage("Username already taken");
        } else {
            getRepository().update(getEntity());
            super.infoMessage("Updated Successfully");
        }
        loadData();
    }

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
