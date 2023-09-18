/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
/**
 *
 * @author avni
 */
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "interest_rate")
    private Double interestRate;
    
    @Column(name = "opened_date")
    @Temporal(TemporalType.DATE)
    private Date openedDate;
    
    @Column(name = "mature_date")
    @Temporal(TemporalType.DATE)
    private Date matureDate;
    
    @Column(name = "balance")
    private Double balance;
    @Column(name = "initial_balance")
    private Double initialBalance;
   
//    (cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Transient
    private transient Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

//    public String getAccType() {
//        return accType;
//    }
//
//    public void setAccType(String accType) {
//        this.accType = accType;
//    }
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public Date getMatureDate() {
        return matureDate;
    }

    public void setMatureDate(Date matureDate) {
        this.matureDate = matureDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
        this.balance = initialBalance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return Objects.equals(this.getId(), account.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.accNo);
        hash = 37 * hash + Objects.hashCode(this.interestRate);
        hash = 37 * hash + Objects.hashCode(this.openedDate);
        hash = 37 * hash + Objects.hashCode(this.matureDate);
        hash = 37 * hash + Objects.hashCode(this.balance);
        hash = 37 * hash + Objects.hashCode(this.initialBalance);
        hash = 37 * hash + Objects.hashCode(this.client);
        hash = 37 * hash + Objects.hashCode(this.product);
        hash = 37 * hash + Objects.hashCode(this.amount);
        return hash;
    }
    

}
