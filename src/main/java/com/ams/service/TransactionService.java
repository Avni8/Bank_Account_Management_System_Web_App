/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.service;

import com.ams.model.Account;
import com.ams.model.AccountMIS;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.TransactionType;
import com.ams.model.User;
import com.ams.repository.AccountMISRepository;
import com.ams.repository.AccountRepository;
import com.ams.repository.AccountTransactionDetailsRepository;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@Stateless
public class TransactionService {

    @Inject
    private AccountMISRepository accountMISRepository;

    @Inject
    private AccountTransactionDetailsRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    public boolean performDeposit(User selectedUser, List<Account> accountList) {

        boolean depositSuccessful = false;

        try {

            if (selectedUser != null) {

//            List<Account> userAccounts = accountRepository.getAccountsByUser(selectedUser);
                for (Account account : accountList) {

                    if (account.getAmount() != null && account.getAmount() > 0) {

                        AccountMIS accountMIS = new AccountMIS();

                        accountMIS.setSourceAccount(account);

                        accountMIS.setTransactionType(TransactionType.DEPOSIT);

                        AccountTransactionDetails accountTransactionDetails
                                = new AccountTransactionDetails();

                        accountTransactionDetails.setDate(new Date());

                        accountTransactionDetails.setCreditAmount(account.getAmount());

                        accountTransactionDetails.setUser(selectedUser.getName());

                        accountTransactionDetails.setAccount(account);

                        Double currentBalance = account.getBalance();
                        Double newBalance = currentBalance + account.getAmount();
                        account.setBalance(newBalance);

                        try {

                            accountMISRepository.save(accountMIS);
                            transactionRepository.save(accountTransactionDetails);
                            accountRepository.update(account);
                            depositSuccessful = true;

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return depositSuccessful;
    }
}
