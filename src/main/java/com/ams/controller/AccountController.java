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
public class AccountController implements Serializable{
    
    private Account account;
    private List<Account> accountList;

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
    }

    public void beforeUpdate(Account account) {
        this.account = account;
    }

    public void createUpdate() {
        
        User user = userRepository.findById(account.getUser().getId());
        account.setUser(user);
        
        Product product = productRepository.findById(account.getProduct().getId());
        account.setProduct(product);
        
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
}
