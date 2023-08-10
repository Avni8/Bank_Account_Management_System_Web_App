/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.service;

import com.ams.model.Account;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.BalanceView;
import com.ams.repository.AccountTransactionDetailsRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@Stateless
public class StatementService {
    
    @Inject 
    AccountTransactionDetailsRepository transactionRepository;
    
   public List<AccountTransactionDetails> loadTransactionDetails(BalanceView balanceView, Account selectedAccount) {
        if (selectedAccount != null) {
            balanceView.setAccount(selectedAccount);
            List<AccountTransactionDetails> transactionDetails = transactionRepository.
                    getTransactionsByAccount(selectedAccount,
                            balanceView.getFromDate(),
                            balanceView.getToDate());
            
            return transactionDetails;
        }
        return null;
    }

    public BalanceView loadOpeningBalance(Account selectedAccount, BalanceView balanceView) {
        if (selectedAccount != null) {
            balanceView = transactionRepository.getOpeningBalance(balanceView);
        }
        return balanceView;
    }
    
}
