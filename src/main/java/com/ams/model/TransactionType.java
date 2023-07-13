/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.ams.model;

/**
 *
 * @author avni
 */
public enum TransactionType {
    
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    FUND_TRANSFER("Fund Transfer");

    private String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }    
}
