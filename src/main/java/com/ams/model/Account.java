/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

/**
 *
 * @author avni
 */
public class Account implements IModelId {
    private String acc_id;
    private String acc_no;
    private String customerId;
    private String acc_type;
    private double interest_rate;
    private String opened_date;
    private String matured_date;
    private double balance;
    private Customer customer;

    public Account(String acc_id, String acc_no, String customerId, String acc_type, double interest_rate,
            String opened_date, String matured_date, double balance, Customer customer) {
        this.acc_id = acc_id;
        this.acc_no = acc_no;
        this.customerId = customerId;
        this.acc_type = acc_type;
        this.interest_rate = interest_rate;
        this.opened_date = opened_date;
        this.matured_date = matured_date;
        this.balance = balance;
        this.customer = customer;
        
    }
    
    @Override
    public String getId() {
        return acc_id;
    }

    @Override
    public void setId(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getAcc_type() {
        return acc_type;
    }
    public String getTableName(){
        return "account";
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getOpened_date() {
        return opened_date;
    }

    public void setOpened_date(String opened_date) {
        this.opened_date = opened_date;
    }

    public String getMatured_date() {
        return matured_date;
    }

    public void setMatured_date(String matured_date) {
        this.matured_date = matured_date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerId() {
        return customerId;
    
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    
}
