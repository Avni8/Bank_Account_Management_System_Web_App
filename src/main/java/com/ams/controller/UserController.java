/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.Account;
import com.ams.model.AccountHolder;
import com.ams.repository.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.User;
import com.ams.model.UserModel;
import com.ams.repository.AbstractRepository;
import com.ams.repository.AccountHolderRepository;
import com.ams.repository.AccountRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("userController")
public class UserController extends AbstractController {

    private User user;
    private User selectedUser;
    private List<User> userList;
    private List<Account> accountList;
    private UserModel userModel;
    private AccountHolder accountHolder;
    private List<AccountHolder> accountHolderList;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private AccountHolderRepository accountHolderRepository;
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
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

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<AccountHolder> getAccountHolderList() {
        if (accountHolderList == null) {
            accountHolderList = accountHolderRepository.getAccountHoldersByRoleName("Client");
        }
        return accountHolderList;
    }

    public void setAccountHolderList(List<AccountHolder> accountHolderList) {
        this.accountHolderList = accountHolderList;
    }

    
    @PostConstruct
    public void init() {
        user = new User();
        loadData();
//        userModel = new UserModel(userList);
    }

    @Override
    public void loadData() {
        userList = userRepository.findAll();
        userModel = new UserModel(userList);
    }

    public void beforeCreate() {
        user = new User();
    }

    public void beforeUpdate(User user) {
        this.user = user;
    }

    public void retrieveAccounts() {
        if (user != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            selectedUser=user;
            accountList = accountRepository.getAccountsByUser(selectedUser);
        } else {
            accountList = null;
        }
    }

    public UserModel getUserModel() {
        return userModel;
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


