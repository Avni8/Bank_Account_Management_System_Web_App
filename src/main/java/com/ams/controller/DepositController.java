/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.Account;
import com.ams.model.AccountMIS;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.ActionType;
import com.ams.model.TransactionType;
import com.ams.model.Client;
import com.ams.model.ResourceType;
import com.ams.repository.AbstractRepository;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import com.ams.repository.ClientRepository;
import com.ams.service.TransactionService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("depositController")
public class DepositController extends AbstractMessageController {

    private List<Client> clientList;
    private List<Account> accountList;
    private Client client;
    private Account account;
    private Client selectedClient;
    private Account selectedAccount;
    private Double amount;

//    private Double depositAmount;

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    @Inject
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private TransactionService transactionService;

   

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

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @PostConstruct
    public void init() {
//        selectedAccount = new Account();
        loadData();
    }

    public void loadData() {
        accountList = accountRepository.findAll();

    }

    public void beforeCreate() {
        selectedAccount = new Account();
        selectedClient = new Client();

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

    public void onUserSelect() {
        if (selectedClient != null) {
            retrieveAccounts(); // Call the method to fetch accounts based on the selected user
            if (accountList != null && !accountList.isEmpty()) {
                // Set the first account as the default selected account
                selectedAccount = accountList.get(0);
            } else {
                selectedAccount = null;
            }
        } else {
            // If no user is selected, reset the selected account as well
            selectedAccount = null;
        }
    }

    public void retrieveAccounts() {
        if (selectedClient != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            accountList = accountRepository.getAccountsByUser(selectedClient);
        } else {
            accountList = null;
        }

    }

    public void beforeDeposit(Client client) {
        this.selectedClient = client != null ? client : null;
        this.accountList = this.selectedClient  != null
                ? accountRepository.getAccountsByUser(selectedClient) : null;
    }

    @RequiredPermission(action = ActionType.DEPOSIT, resource = ResourceType.ACCOUNT)
    public void deposit() {

        boolean depositSuccessful = transactionService.performDeposit(selectedClient, accountList);

        if (depositSuccessful) {
            super.infoMessage("Deposit Successfull");
        } else {
            super.errorMessage("Deposit Failed");
        }
    }
}
