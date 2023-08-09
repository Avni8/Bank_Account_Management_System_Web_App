/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import java.util.List;

/**
 *
 * @author avni
 */
public class DepositRequest {

    private Long userId;
    private List<AccountRequest> accountList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AccountRequest> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountRequest> accountList) {
        this.accountList = accountList;
    }

    
}