/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

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
import com.ams.repository.ProductRepository;
import com.ams.repository.UserRepository;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("accountController")
public class AccountController implements Serializable {

    private Account account;
    private List<Account> accountList;
    private List<User> userList;
    private List<Product> productList;
    private User user;
    private Product product;

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

    private void loadData() {
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

    public void createUpdate() {

        if (account.getId() == null) {
            accountRepository.save(account);
        } else {
            accountRepository.update(account);
        }
        loadData();
    }

    public void delete(Account account) {
        accountRepository.delete(account.getId());
        loadData();
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

}
