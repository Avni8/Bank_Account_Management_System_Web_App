/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.response;

import com.ams.model.AccountTransactionDetails;
import java.util.List;

/**
 *
 * @author avni
 */
public class StatementResponse {
    
    private List<AccountTransactionDetails> transactionDetails;
    private Double balanceUptoFromDate;
    private Double latestBalance;

    public List<AccountTransactionDetails> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<AccountTransactionDetails> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Double getBalanceUptoFromDate() {
        return balanceUptoFromDate;
    }

    public void setBalanceUptoFromDate(Double balanceUptoFromDate) {
        this.balanceUptoFromDate = balanceUptoFromDate;
    }

    public Double getLatestBalance() {
        return latestBalance;
    }

    public void setLatestBalance(Double latestBalance) {
        this.latestBalance = latestBalance;
    }
}
