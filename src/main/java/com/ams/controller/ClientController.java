/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.Account;
import com.ams.model.ActionType;
import com.ams.model.User;
import com.ams.repository.ClientRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ams.model.Client;
import com.ams.model.ResourceType;
import com.ams.model.UserModel;
import com.ams.repository.AbstractRepository;
import com.ams.repository.UserRepository;
import com.ams.repository.AccountRepository;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("clientController")
public class ClientController extends AbstractController {

    private Client client;
    private Client selectedClient;
    private List<Client> clientList;
    private List<Account> accountList;
    private UserModel userModel;
    private User user;
    private List<User> userList;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserRepository userRepository;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
         if (userList == null) {
            userList = userRepository.getUserWithNoClientModel();
        }
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    @PostConstruct
    public void init() {
        client = new Client();
        loadData();
//        userModel = new UserModel(userList);
    }

    @Override
    public void loadData() {
        clientList = clientRepository.findAll();
        userModel = new UserModel(clientList);
    }

    public void beforeCreate() {
        client = new Client();
    }

    public void beforeUpdate(Client client) {
        this.client = client;
    }

    public void retrieveAccounts() {
        if (client != null) {
            // Call your repository or service to fetch the list of accounts based on the selected user
            selectedClient = client;
            accountList = accountRepository.getAccountsByUser(selectedClient);
        } else {
            accountList = null;
        }
    }
    
    @Override
    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.CLIENT)
    public void createUpdate(){
        super.createUpdate();
        
    }
    
    @RequiredPermission(action = ActionType.DELETE, resource = ResourceType.CLIENT)
    public void delete(Client client){
        clientRepository.delete(client.getId());
        loadData();
    }
    
    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public AbstractRepository getRepository() {
        return clientRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return client;
    }
    
}


