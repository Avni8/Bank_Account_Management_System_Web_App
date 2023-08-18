/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.Account;
import com.ams.model.AccountMIS;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.TransactionType;
import com.ams.model.Client;
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
@Named("withdrawController")
public class WithdrawController extends AbstractMessageController {

    private List<Client> clientList;
    private List<Account> accountList;
    private Client client;
    private Account account;
    private Client selectedClient;
    private Account selectedAccount;
    private Double withdrawAmount;
    @Inject
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private ClientRepository userRepository;

    @Inject
    private ClientController userController;

    @Inject
    private TransactionService transactionService;

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

    public Double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    @PostConstruct
    public void init() {
        selectedAccount = new Account();
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

    public void retrieveAccounts() {
        if (selectedClient != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            accountList = accountRepository.getAccountsByUser(selectedClient);
        } else {
            accountList = null;
        }

    }

    public void beforeWithdrawal(Client client) {
        this.selectedClient = client != null ? client : null;
        this.accountList = this.selectedClient != null
                ? accountRepository.getAccountsByUser(selectedClient) : null;
    }

    public void withdraw() {

//        boolean lowBalance = transactionService.checkBalance(accountList);
//        if (lowBalance) {
//
//            super.warningMessage("Insufficient Funds: The withdraw amount "
//                    + "exceeds the available balance");
//        } else {
            boolean withdrawSuccessful = transactionService.performWithdrawal(selectedClient, accountList);

            if (withdrawSuccessful) {
                super.infoMessage("Amount successfully withdrawn");
            } else {
                super.errorMessage("Withdrawal failed");
            }
        }

//    }

}
