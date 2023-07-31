/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avni
 */
public class BalanceView {
    
    private Account account;
    private Date fromDate;
    private Date toDate;
    private Double balanceUptoFromDate;
    private Double balanceUptoToDate;
  
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Double getBalanceUptoFromDate() {
        return balanceUptoFromDate;
    }

    public void setBalanceUptoFromDate(Double balanceUptoFromDate) {
        this.balanceUptoFromDate = balanceUptoFromDate;
    }

    public Double getBalanceUptoToDate() {
        return balanceUptoToDate;
    }

    public void setBalanceUptoToDate(Double balanceUptoToDate) {
        this.balanceUptoToDate = balanceUptoToDate;
    }

    public Double getOpeningBalance() {
        if(account != null){
            Double openingBalance = account.getBalance() - balanceUptoToDate;
            return openingBalance;
        }
        return 0.0;
    }
}
