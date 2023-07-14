/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.AccountMIS;
import com.ams.model.TransactionType;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import java.io.Serializable;
import java.util.Arrays;
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
@Named("accountMISController")
public class AccountMISController implements Serializable {
    
    private AccountMIS accountMIS;
    private List<TransactionType> transactionTypes;
    private List<AccountMIS> accountMISList;
    private List<Account> accountList;

    @Inject 
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountRepository accountRepository;
    
    
    public AccountMISController() {
        accountMIS = new AccountMIS();
        accountMIS.setSourceAccount(new Account());
        transactionTypes = Arrays.asList(TransactionType.values());
    }
   
    public List<AccountMIS> getAccountMISList() {
        return accountMISList;
    }

    public void setAccountMISList(List<AccountMIS> accountMISList) {
        this.accountMISList = accountMISList;
    }
    
    public List<TransactionType> getTransactionTypes() {
        return transactionTypes;
    }
   
    @PostConstruct
    public void init() {
        loadData();
    }

    private void loadData() {
        accountMISList = accountMISRepository.findAll();
    }

    public void beforeCreate() {
        accountMIS = new AccountMIS();
    }

    public void beforeUpdate(AccountMIS accountMIS) {
        this.accountMIS = accountMIS;
    }

    public void createUpdate() {

        if (accountMIS.getId() == null) {
            accountMISRepository.save(accountMIS);
        } else {
            accountMISRepository.update(accountMIS);
        }
        loadData();
    }

    public void delete(AccountMIS accountMIS) {
        accountMISRepository.delete(accountMIS.getId());
        loadData();
    }
    
    public AccountMIS getAccountMIS() {
        return accountMIS;
    }

    public void setAccountMIS(AccountMIS accountMIS) {
        this.accountMIS = accountMIS;
    }
    
    public List<Account> getAccountList() {
        
        if (accountList == null) {
            accountList = accountRepository.findAll();
        }
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
