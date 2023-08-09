/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.repository.UserRepository;
import com.ams.repository.AccountRepository;
import com.ams.service.TransactionService;
import com.ams.model.User;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.ams.service.TransactionService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avni
 */
@RequestScoped
@Path("/deposit")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepositResource {

    @Inject
    TransactionService transactionService;

    @Inject
    UserRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @POST
    public Response deposit(
            @QueryParam("userId") Long userId,
            @QueryParam("accountIds") List<Long> accountIds,
            @QueryParam("depositAmount") Double amount) {

        User selectedUser = userRepository.findById(userId);
        List<Account> accountList = accountRepository.getAccountsByIds(accountIds);
        transactionService.performDeposit(selectedUser, accountList, amount);
        return Response.ok("Deposit successful").build();
    }
}
