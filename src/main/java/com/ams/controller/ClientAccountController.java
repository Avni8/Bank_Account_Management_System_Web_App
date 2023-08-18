/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.repository.AccountRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("clientAccountController")
public class ClientAccountController extends AbstractMessageController{
    
    private Client loggedInUser;
    
    @Inject
    private AccountRepository accountRepository;

    private List<Account> userAccounts;
    
    private Account selectedAccount;

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public Client getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Client loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    
    
     @PostConstruct
    public void init() {
        loadUserAccounts();
    }
    
    public void loadUserAccounts() {
    loggedInUser = (Client) FacesContext.getCurrentInstance()
                          .getExternalContext().getSessionMap().get("loggedInClient");
    if (loggedInUser != null) {
        userAccounts = accountRepository.getAccountsByUser(loggedInUser);
    }
    }
    
}
