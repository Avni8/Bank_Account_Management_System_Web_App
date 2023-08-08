/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.User;
import com.ams.repository.AccountRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author avni
 */
@RequestScoped
@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource implements Serializable{
    
    @Inject
    AccountRepository accountRepository;

    @GET
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Account getAccountById(@PathParam("id") long id) {
        return accountRepository.findById(id);
    }

    @POST
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @PUT
    @Path("/{id}")
    public void updateAccount(@PathParam("id") long id, Account account) {
        accountRepository.update(account);
    }

    @DELETE
    @Path("/{id}")
    public void deleteAccount(@PathParam("id") long id) {
        accountRepository.delete(id);
    }
}
