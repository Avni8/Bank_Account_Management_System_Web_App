/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.BalanceView;
import com.ams.model.Client;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import com.ams.repository.ClientRepository;
import com.ams.service.StatementService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("statementController")
public class StatementController extends AbstractMessageController {

    private List<Client> clientList;
    private List<Account> accountList;
    private List<AccountTransactionDetails> transactionDetails;
    private Client client;
    private Account account;
    private Client selectedClient;
    private Account selectedAccount;
    private BalanceView balanceView;

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
    private StatementService statementService;

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

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public List<AccountTransactionDetails> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<AccountTransactionDetails> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public BalanceView getBalanceView() {
        return balanceView;
    }

    public void setBalanceView(BalanceView balanceView) {
        this.balanceView = balanceView;
    }

    @PostConstruct
    public void init() {
//        selectedClient = new Client();
//        selectedAccount = new Account();
        balanceView = new BalanceView();
        loadData();
    }

    private void loadData() {
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

    public void beforeViewStatement(Client user) {
        selectedClient = new Client();
        selectedAccount = new Account();
        balanceView.setFromDate(null);
        balanceView.setToDate(null);
        balanceView.setBalanceUptoFromDate(null);
        transactionDetails = null;
        this.selectedClient = user != null ? user : null;
        this.accountList = this.selectedClient != null
                ? accountRepository.getAccountsByUser(selectedClient) : null;
    }

    public void beforeClientViewStatement(Client user) {

//        selectedAccount = new Account();
        this.selectedAccount = account != null ? account : null;
        balanceView.setFromDate(null);
        balanceView.setToDate(null);
        balanceView.setBalanceUptoFromDate(null);
        transactionDetails = null;

    }

    public void loadTransactionDetails() {
//        if (selectedAccount != null) {
//            balanceView.setAccount(selectedAccount);
//            transactionDetails = transactionRepository.
//                    getTransactionsByAccount(selectedAccount,
//                            balanceView.getFromDate(),
//                            balanceView.getToDate());
//            loadOpeningBalance();
//        }

        if (selectedAccount != null) {
            transactionDetails = statementService.loadTransactionDetails(balanceView, selectedAccount);
            balanceView = statementService.loadOpeningBalance(selectedAccount, balanceView);
        }

    }

//    public void loadOpeningBalance() {
//        if (selectedAccount != null) {
//            balanceView = transactionRepository.getOpeningBalance(balanceView);
//        }
//    }
}
