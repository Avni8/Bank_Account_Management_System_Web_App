/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.repository.AccountRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.Client;
import com.ams.model.Product;
import com.ams.model.Account;
import com.ams.model.ActionType;
import com.ams.model.ResourceType;
import com.ams.repository.AbstractRepository;
import com.ams.repository.ProductRepository;
import com.ams.repository.ClientRepository;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("accountController")
public class AccountController extends AbstractController {

    private Account account;
    private List<Account> accountList;
    private List<Client> clientList;
    private List<Product> productList;
    private Client client;
    private Product product;
    private DepositController depositController;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private ProductRepository productRepository;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PostConstruct
    public void init() {
        account = new Account();
        loadData();
    }

    @Override
    public void loadData() {
        accountList = accountRepository.findAll();
    }

    public void beforeCreate() {
        account = new Account();
        client = new Client();
        product = new Product();
    }

    public void beforeUpdate(Account account) {
        this.account = account;
    }

    @Override
    public AbstractRepository getRepository() {
        return accountRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return account;
    }

    public Client getSelectedClient() {
        return client;
    }

    public void setSelectedClient(Client client) {
        this.client = client;
    }

    public List<Client> getClientList() {
        if (clientList == null) {
            clientList = clientRepository.findAll();
        }
        return clientList;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            productList = productRepository.findAll();
        }
        return productList;
    }

    @Override
    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.ACCOUNT)
    public void createUpdate() {
        super.createUpdate();

    }

    @RequiredPermission(action = ActionType.DELETE, resource = ResourceType.ACCOUNT)
    public void delete(Account account) {
        accountRepository.delete(account.getId());
        loadData();
    }

    public DepositController getDepositController() {
        return depositController;
    }

    public void setDepositController(DepositController depositController) {
        this.depositController = depositController;
    }

}
