/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.AccountHolder;
import com.ams.model.UserRole;
import com.ams.repository.AbstractRepository;
import com.ams.repository.AccountHolderRepository;
import com.ams.repository.UserRoleRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("accountHolderController")
public class AccountHolderController extends AbstractController {

    private AccountHolder accountHolder;
    private List<AccountHolder> accountHolderList;
    private List<UserRole> roleList;

    @Inject
    AccountHolderRepository accountHolderRepository;

    @Inject
    UserRoleRepository roleRepository;

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<AccountHolder> getAccountHolderList() {
        return accountHolderList;
    }

    public void setAccountHolderList(List<AccountHolder> accountHolderList) {
        this.accountHolderList = accountHolderList;
    }

    @PostConstruct
    public void init() {
        loadData();
    }

    public List<UserRole> getRoleList() {
        if (roleList == null) {
            roleList = roleRepository.findAll();
        }
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public void beforeCreate() {
        accountHolder = new AccountHolder();
    }

    public void beforeUpdate(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public void loadData() {

        accountHolderList = accountHolderRepository.findAll();

    }

    public void createUserAccount() {

        if (!isUsernameTaken()) {
            super.createUpdate();
        } else {
            super.warningMessage("Username already taken");

        }

    }

    public boolean isUsernameTaken() {

        AccountHolder accHolder = new AccountHolder();
        accHolder = accountHolderRepository.findByUsername(accountHolder.getUsername());
        if (accHolder != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AbstractRepository getRepository() {
        return accountHolderRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return accountHolder;
    }

}
