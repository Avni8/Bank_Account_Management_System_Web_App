/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author avni
 */
@RequestScoped
@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource implements Serializable {

    @Inject
    AccountRepository accountRepository;

    @POST
    public Response createAccount(Account account) throws JsonProcessingException {
        accountRepository.save(account);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(account);

        return RestResponse.responseBuilder("true", "201", "Account created successfully", str);
    }

    @GET
    public Response getAllAccounts() throws JsonProcessingException {
        List<Account> accountList = accountRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(accountList);
        return RestResponse.responseBuilder("true", "200", "List of accounts", str);
    }

    @GET
    @Path("/{id}")
    public Response getAccountById(@PathParam("id") long id) throws JsonProcessingException {
        Account account = accountRepository.findById(id);
        if (account == null) {

            return RestResponse.responseBuilder("false", "404", " Account with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(account);
        return RestResponse.responseBuilder("true", "200", "Account with id " + id + " found", str);

    }

    @PUT
    @Path("/{id}")
    public Response updateAccount(@PathParam("id") long id, Account account) throws JsonProcessingException {

        Account acc = accountRepository.findById(id);
        if (acc == null) {
            return RestResponse.responseBuilder("false", "404", " Account with id " + id + " not found", null);
        }
        if (!account.getId().equals(acc.getId())) {
            return RestResponse.responseBuilder("false", "404", " Account id mismatch", null);
        }
        accountRepository.update(account);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(account);

        return RestResponse.responseBuilder("true", "200", " Account updated successfully", str);

    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") long id) throws JsonProcessingException {
        Account account = accountRepository.findById(id);
        if (account == null) {
            return RestResponse.responseBuilder("false", "404", " Account with id " + id + " not found", null);
        }
        accountRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "Account with id " + id + " deleted successfully", null);
    }
}
