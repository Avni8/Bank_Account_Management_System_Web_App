/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.ams.model.Transaction;
import com.ams.model.User;
import com.ams.repository.AccountRepository;
import com.ams.repository.TransactionRepository;
import com.ams.repository.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("transactionController")
public class TransactionController implements Serializable {
    
    private Transaction transaction;
    private List<Transaction> transactionList;
    private List<User> userList;
    private List<Account> accountList;
    private User user;
    private Account account;

    @Inject
    private TransactionRepository transactionRepository;
    
    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserRepository userRepository;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @PostConstruct
    public void init() {
        transaction = new Transaction();
        loadData();
    }

    private void loadData() {
        transactionList = transactionRepository.findAll();
    }

    public void beforeCreate() {
        transaction = new Transaction();
    }

    public void beforeUpdate(Transaction transaction) {
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
    
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction.getId());
        loadData();
    }
    
     public List<User> getUserList() {
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
