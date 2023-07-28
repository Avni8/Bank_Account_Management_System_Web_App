/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.repository.AccountRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.User;
import com.ams.model.Product;
import com.ams.model.Account;
import com.ams.repository.AbstractRepository;
import com.ams.repository.ProductRepository;
import com.ams.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("accountController")
public class AccountController extends AbstractController {

    private Account account;
    private List<Account> accountList;
    private List<User> userList;
    private List<Product> productList;
    private User user;
    private Product product;
    private DepositController depositController;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ProductRepository productRepository;
    
    

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PostConstruct
    public void init() {
        account = new Account();
        loadData();
    }

    @Override
    public void loadData() {
        accountList = accountRepository.findAll();
    }

    public void beforeCreate() {
        account = new Account();
        user = new User();
        product = new Product();
    }

    public void beforeUpdate(Account account) {
        this.account = account;
    }

    @Override
    public AbstractRepository getRepository() {
        return accountRepository;
    }
    
    @Override
    public AbstractEntity getEntity(){
        return account;
    }

    public User getSelectedUser() {
        return user;
    }

    public void setSelectedUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        if (userList == null) {
            userList = userRepository.findAll();
        }
        return userList;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            productList = productRepository.findAll();
        }
        return productList;
    }

    public DepositController getDepositController() {
        return depositController;
    }

    public void setDepositController(DepositController depositController) {
        this.depositController = depositController;
    }
    
    
    public List<User> getFilteredUserList() {
        Set<String> uniqueNames = new HashSet<>();
        List<User> filteredList = new ArrayList<>();

        for (User user : userList) {
            if (!uniqueNames.contains(user.getName())) {
                filteredList.add(user);
                uniqueNames.add(user.getName());
            }
          
        }
        if (depositController.getSelectedUser() != null && userList.contains(depositController.getSelectedUser())) {
            filteredList.remove(depositController.getSelectedUser());
        }

        return filteredList;
    }
}
