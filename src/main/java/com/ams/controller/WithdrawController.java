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
public class WithdrawController implements Serializable {

    private List<User> userList;
    private List<Account> accountList;
    private User user;
    private Account account;
    private User selectedUser;
    private Account selectedAccount;
    private Double withdrawAmount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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

    private void loadData() {
        accountList = accountRepository.findAll();

    }

    public void beforeCreate() {
        selectedAccount = new Account();
        selectedUser = new User();

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
        if (selectedUser != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            accountList = accountRepository.getAccountsByUser(selectedUser);
        } else {
            accountList = null;
        }

    }
    
    public void beforeWithdrawal(User user) {
            this.selectedUser = user != null ? user:null;
            this.accountList = this.selectedUser != null
                    ? accountRepository.getAccountsByUser(selectedUser):null;
    }


    public void withdraw() {
        
        if (selectedUser != null) {

            for (Account account : accountList) {

                if (account.getAmount() != null && account.getAmount() > 0) {

                    AccountMIS accountMIS = new AccountMIS();

                    accountMIS.setSourceAccount(account);

                    accountMIS.setTransactionType(TransactionType.WITHDRAWAL);

                    AccountTransactionDetails accountTransactionDetails =
                            new AccountTransactionDetails();

                    accountTransactionDetails.setDate(new Date());

                    accountTransactionDetails.setDebitAmount(account.getAmount());

                    accountTransactionDetails.setUser(selectedUser.getName());

                    accountTransactionDetails.setAccount(account);

                    Double currentBalance = account.getBalance();
                    Double newBalance = currentBalance - account.getAmount();
                    account.setBalance(newBalance);

                    accountMISRepository.save(accountMIS);
                    transactionRepository.save(accountTransactionDetails);
                    accountRepository.update(account);
                    
                    FacesContext context = FacesContext.getCurrentInstance();

                    context.addMessage(null, new FacesMessage("Withdrawal Successful"));

                }

            }

        }

        
        

    }

}
