/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author avni
 */

@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {
    
    private LocalDate date;
    @Column(name = "credit_amount")
    private Double creditAmount;
    @Column(name = "debit_amount")
    private Double debitAmount;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Account account;
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
