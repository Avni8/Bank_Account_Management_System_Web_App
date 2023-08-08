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
import javax.ws.rs.core.Response;

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
    public Response getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        ApiResponse<List<Account>> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", accounts);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getAccountById(@PathParam("id") long id) {
        Account account = accountRepository.findById(id);
        if (account != null) {
            ApiResponse<Account> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", account);
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } else {
            ApiResponse<Account> response = new ApiResponse<>(Response.Status.NOT_FOUND.getStatusCode(), "Account not found", null);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }
    }

    @POST
    public Response createAccount(Account account) {
        Account createdAccount = accountRepository.save(account);
        ApiResponse<Account> response = new ApiResponse<>(Response.Status.CREATED.getStatusCode(), "Account sucessfully created", createdAccount);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAccount(@PathParam("id") long id, Account account) {
        accountRepository.update(account);
        ApiResponse<Account> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Account sucessfully updated", account);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") long id) {
        accountRepository.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Account successfully deleted", null);
        return Response.status(Response.Status.OK)
                       .entity(response)
                       .build();
    }
    

}