/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author avni
 */
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {
    
    @Column(name = "acc_no")
    private String accNo;
//    @Column(name = "acc_type")
//    private String accType;
    @Column(name = "interest_rate")
    private Double interestRate;
    @Column(name = "opened_date")
    private String openedDate;
    @Column(name = "mature_date")
    private String matureDate;
    @Column(name = "balance")
    private Double balance;
    
    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
//    @JoinColumn(name = "product_id")
    private Product product;
    
    public Account() {
        this.user = new User();
        this.product = new Product();
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

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getMatureDate() {
        return matureDate;
    }

    public void setMatureDate(String matureDate) {
        this.matureDate = matureDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }  
}
