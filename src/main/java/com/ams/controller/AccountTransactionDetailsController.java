/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.Client;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import com.ams.repository.ClientRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("transactionController")
public class AccountTransactionDetailsController implements Serializable {
    
    private AccountTransactionDetails transaction;
    private List<AccountTransactionDetails> transactionList;
    private List<Client> userList;
    private List<Account> accountList;
    private Client user;
    private Account account;

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;
    
    @Inject
    private AccountRepository accountRepository;

    @Inject
    private ClientRepository userRepository;

    public AccountTransactionDetails getTransaction() {
        return transaction;
    }

    public void setTransaction(AccountTransactionDetails transaction) {
        this.transaction = transaction;
    }

    public List<AccountTransactionDetails> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AccountTransactionDetails> transactionList) {
        this.transactionList = transactionList;
    }

    @PostConstruct
    public void init() {
//        transaction = new Transaction();
        loadData();
    }

    private void loadData() {
        transactionList = transactionRepository.findAll();
    }

    public void beforeCreate() {
        transaction = new AccountTransactionDetails();
    }

    public void beforeUpdate(AccountTransactionDetails transaction) {
        this.transaction = transaction;
    }

    public void createUpdate() {
        if (transaction.getId() == null) {
            transactionRepository.save(transaction);
        } else {
            transactionRepository.update(transaction);
        }
        loadData();
    }
    
    public void delete(AccountTransactionDetails transaction) {
        transactionRepository.delete(transaction.getId());
        loadData();
    }
    
     public List<Client> getUserList() {
        if (userList == null) {
            userList = userRepository.findAll();
        }
        return userList;
    }

    public List<Account> getAccountList() {
        if (accountList == null) {
            accountList = accountRepository.findAll();
        }
        return accountList;
    }
 
}
