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
import static org.primefaces.component.effect.Effect.PropertyKeys.event;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("clientAccountController")
public class ClientAccountController extends AbstractMessageController{
    
    private Client loggedInUser;
    private List<Account> userAccounts;
    private Account selectedAccount;
    private Account account;
    
    @Inject
    private AccountRepository accountRepository;
    
    @Inject
    private UserBean userBean;
             
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
     @PostConstruct
    public void init() {
        loadUserAccounts();
    }
    
    public void loadUserAccounts() {
    loggedInUser = userBean.getCurrentClient();
    if (loggedInUser != null) {
        userAccounts = accountRepository.getAccountsByUser(loggedInUser);
    }
    }
    
    public void onAccountSelect(){
        
        if(account != null){
            
            selectedAccount = account;
            System.out.println("Selected Account: "+ selectedAccount);
        }
        
    }
    
}
