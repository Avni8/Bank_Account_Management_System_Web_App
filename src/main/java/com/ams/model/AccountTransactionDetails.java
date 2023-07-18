/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author avni
 */
@Entity
@Table(name="transaction")
public class AccountTransactionDetails extends AbstractEntity{
    
    @Column(name =  "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "credit_amount")
    private Double creditAmount;
    @Column(name = "debit_amount")
    private Double debitAmount;  
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    private String user;
//    public AccountMIS getAccountMIS() {
//        return accountMIS;
//    }
//
//    public void setAccountMIS(AccountMIS accountMIS) {
//        this.accountMIS = accountMIS;
//    }
  
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }
}
