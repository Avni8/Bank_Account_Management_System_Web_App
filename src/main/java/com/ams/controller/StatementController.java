/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.User;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import com.ams.repository.UserRepository;
import java.io.Serializable;
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
@Named("statementController")
public class StatementController implements Serializable{
    
    private List<User> userList;
    private List<Account> accountList;
    private List<AccountTransactionDetails> transactionDetails;
    private User user;
    private Account account;
    private User selectedUser;
    private Account selectedAccount;
    
    
    @Inject
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private UserController userController;
    
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public List<AccountTransactionDetails> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<AccountTransactionDetails> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
    
     @PostConstruct
    public void init() {
//        selectedAccount = new Account();
        loadData();
    }

    private void loadData() {
        accountList = accountRepository.findAll();

    }

    public void beforeCreate() {
        selectedAccount = new Account();
        selectedUser = new User();

    }

    public void beforeUpdate(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public void createUpdate() {

        if (selectedAccount.getId() == null) {
            accountRepository.save(selectedAccount);
        } else {
            accountRepository.update(selectedAccount);
        }
        loadData();
    }

    public void delete(Account account) {
        accountRepository.delete(account.getId());
        loadData();
    }
    
    public void retrieveAccounts() {
        if (selectedUser != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            accountList = accountRepository.getAccountsByUser(selectedUser);
        } else {
            accountList = null;
        }

    }
    public void beforeViewStatement(User user) {
            this.selectedUser = user != null ? user:null;
            this.accountList = this.selectedUser != null
                    ? accountRepository.getAccountsByUser(selectedUser):null;
    }
    
    
    public void loadTransactionDetails(){
        
        if(selectedAccount != null){
            
            transactionDetails = transactionRepository.getTransactionsByAccount
        (selectedAccount);
            
        }
        
        
    }
    
    
    
    
    
    

    
    
}
