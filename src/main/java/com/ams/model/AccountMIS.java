/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author avni
 */
@Entity
@Table(name = "accountmis")
public class AccountMIS extends AbstractEntity {

    @Column(name = "trasaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "source_account_number")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_number")
    private Account destinationAccount;

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.transactionType);
        hash = 29 * hash + Objects.hashCode(this.sourceAccount);
        hash = 29 * hash + Objects.hashCode(this.destinationAccount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountMIS other = (AccountMIS) obj;
        if (!Objects.equals(this.transactionType, other.transactionType)) {
            return false;
        }
        if (!Objects.equals(this.sourceAccount, other.sourceAccount)) {
            return false;
        }
        return Objects.equals(this.destinationAccount, other.destinationAccount);
    }

    @Override
    public String toString() {
        return "" + getId();
    }

}
