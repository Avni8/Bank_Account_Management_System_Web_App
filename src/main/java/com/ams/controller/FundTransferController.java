/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Account;
import com.ams.model.AccountMIS;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.TransactionType;
import com.ams.model.User;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import com.ams.repository.UserRepository;
import java.io.Serializable;
import java.util.Date;
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
@Named("fundtransferController")
public class FundTransferController implements Serializable {

    private List<User> userList;
    private List<Account> sourceAccountList;
    private List<Account> destinationAccountList;
    private User user;
    private Account account;
    private User selectedUser;
    private User fromUser;
    private User toUser;
    private Account sourceAccount;
    private Account destinationAccount;
    private Double transferAmount;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
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

//    @PostConstruct
//    public void init() {
//        account = new Account();
//        loadData();
//    }
//
//    private void loadData() {
//        accountList = accountRepository.findAll();
//
//    }
//    public void beforeCreate() {
//        selectedAccount = new Account();
//        selectedUser = new User();
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
        if (userController.getSelectedUser() != null) {
            
            fromUser = userController.getSelectedUser();
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

     public void beforeTransfer(){
        if(userController.getUser() != null){
            
            setFromUser(userController.getUser());
        }
        else{
            setFromUser(null);
        }
    }

    public void transferFunds() {

        if (sourceAccount != null && destinationAccount != null && transferAmount != null
                && transferAmount > 0) {

            if (sourceAccount.getBalance() > transferAmount) {

                AccountMIS sourceAccountMIS = new AccountMIS();
                AccountTransactionDetails sourceAccountTransactionDetails = new AccountTransactionDetails();

                sourceAccountMIS.setTransactionType(TransactionType.FUND_TRANSFER);
                sourceAccountMIS.setSourceAccount(sourceAccount);
                sourceAccountMIS.setDestinationAccount(destinationAccount);
                accountMISRepository.save(sourceAccountMIS);

                sourceAccountTransactionDetails.setDate(new Date());
                sourceAccountTransactionDetails.setDebitAmount(transferAmount);
                sourceAccountTransactionDetails.setUser(fromUser.getName());
                sourceAccountTransactionDetails.setAccount(sourceAccount);
                transactionRepository.save(sourceAccountTransactionDetails);

                Double currentSourceAccountBalance = sourceAccount.getBalance();
                Double newSourceAccountBalance = currentSourceAccountBalance - transferAmount;
                sourceAccount.setBalance(newSourceAccountBalance);
                accountRepository.update(sourceAccount);

                AccountMIS destinationAccountMIS = new AccountMIS();
                AccountTransactionDetails destinationAccountTransactionDetails = new AccountTransactionDetails();

                destinationAccountMIS.setTransactionType(TransactionType.FUND_TRANSFER);
                destinationAccountMIS.setSourceAccount(sourceAccount);
                destinationAccountMIS.setDestinationAccount(destinationAccount);
                accountMISRepository.save(destinationAccountMIS);

                destinationAccountTransactionDetails.setDate(new Date());
                destinationAccountTransactionDetails.setCreditAmount(transferAmount);
                destinationAccountTransactionDetails.setUser(toUser.getName());
                destinationAccountTransactionDetails.setAccount(destinationAccount);
                transactionRepository.save(destinationAccountTransactionDetails);

                Double currentDestinationAccountBalance = destinationAccount.getBalance();
                Double newDestinationAccountBalance = currentDestinationAccountBalance
                        + transferAmount;
                destinationAccount.setBalance(newDestinationAccountBalance);
                accountRepository.update(destinationAccount);

            }

        }

    }
}
