/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.repository.UserRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.User;
import com.ams.model.UserModel;
import com.ams.repository.AccountRepository;
import java.util.ArrayList;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("userController")
public class UserController implements Serializable {

    private User user;
    private User selectedUser;
    private List<User> userList;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    private List<Account> accountList;
    private UserModel userModel;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AccountRepository accountRepository;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

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
//        userModel = new UserModel(userList);
    }

    private void loadData() {
        userList = userRepository.findAll();
        userModel = new UserModel(userList);
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

    public void retrieveAccounts() {
        if (user != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            selectedUser=user;
            accountList = accountRepository.getAccountsByUser(user);
        } else {
            accountList = null;
        }
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
