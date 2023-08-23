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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.messages.Messages;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("fundtransferController")
public class FundTransferController extends AbstractMessageController {

    private List<Client> clientList;
    private List<Account> sourceAccountList;
    private List<Account> destinationAccountList;
    private Account account;
    private Client selectedClient;
    private Client fromUser;
    private Client toUser;
    private Account sourceAccount;
    private Account destinationAccount;
    private Double transferAmount;
    private ClientAccountController clientAccountController;

    @Inject
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private ClientRepository userRepository;

    @Inject
    private TransactionService transactionService;

    public Client getFromUser() {
        return fromUser;
    }

    public void setFromUser(Client fromUser) {
        this.fromUser = fromUser;
    }

    public Client getToUser() {
        return toUser;
    }

    public void setToUser(Client toUser) {
        this.toUser = toUser;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }


    public List<Account> getSourceAccountList() {
        return sourceAccountList;
    }

    public void setSourceAccountList(List<Account> sourceAccountList) {
        this.sourceAccountList = sourceAccountList;
    }

    public List<Account> getDestinationAccountList() {
        return destinationAccountList;
    }

    public void setDestinationAccountList(List<Account> destinationAccountList) {
        this.destinationAccountList = destinationAccountList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public ClientAccountController getClientAccountController() {
        return clientAccountController;
    }

    public void setClientAccountController(ClientAccountController clientAccountController) {
        this.clientAccountController = clientAccountController;
    }

    @PostConstruct
    public void init() {
        account = new Account();

    }
//
//    private void loadData() {
//        accountList = accountRepository.findAll();
//
//    }
//    public void beforeCreate() {
//        selectedAccount = new Account();
//        selectedUser = new Client();
//
//    }
//
//    public void beforeUpdate(Account selectedAccount) {
//        this.selectedAccount = selectedAccount;
//    }
//
//    public void createUpdate() {
//
//        if (selectedAccount.getId() == null) {
//            accountRepository.save(selectedAccount);
//        } else {
//            accountRepository.update(selectedAccount);
//        }
//        loadData();
//    }
//    public void delete(Account account) {
//        accountRepository.delete(account.getId());
//        loadData();
//    }

    public void retrieveSourceAccounts() {
        if (fromUser != null) {
            sourceAccountList = accountRepository.getAccountsByUser(fromUser);
        } else {
            sourceAccountList = null;
        }
    }

    public void retrieveDestinationAccounts() {
        if (toUser != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            destinationAccountList = accountRepository.getAccountsByUser(toUser);
        } else {
            destinationAccountList = null;
        }
    }

    public void beforeTransfer(Client user) {

        this.toUser = new Client();
        this.transferAmount = null;
        this.destinationAccount = null;
        this.fromUser = user != null ? user : null;
        this.sourceAccountList = this.fromUser != null
                ? accountRepository.getAccountsByUser(fromUser) : null;
    }

    public void beforeClientTransfer(Client user) {
        this.sourceAccount = account != null ? account : null;
        this.toUser = new Client();
        this.transferAmount = null;
        this.destinationAccount = null;
    }

    @RequiredPermission(action = ActionType.TRANSFER, resource = ResourceType.ACCOUNT)
    public void transferFunds() {

        if (destinationAccount.getAccNo().equals(sourceAccount.getAccNo())) {

            super.warningMessage("Source and Destination accounts should be different!");
        } else {

            boolean transferSuccessful = transactionService.performFundTransfer(fromUser, toUser, sourceAccount, destinationAccount, transferAmount);

            if (transferSuccessful) {
                super.infoMessage("Fund Transfer Successful");
            } else {
                super.errorMessage("Fund Transfer Failed");
            }
        }

    }

    public void clientTransferFunds() {
        
        if (destinationAccount.getAccNo().equals(sourceAccount.getAccNo())) {

            super.warningMessage("Source and Destination accounts should be different!");
        } else {
            
            Client fromUser = (Client) FacesContext.getCurrentInstance()
                            .getExternalContext().getSessionMap().get("loggedInClient");

            boolean transferSuccessful = transactionService.performFundTransfer(fromUser, toUser, sourceAccount, destinationAccount, transferAmount);

            if (transferSuccessful) {
                super.infoMessage("Fund Transfer Successful");
            } else {
                super.errorMessage("Fund Transfer Failed");
            }
        }
  }
}
