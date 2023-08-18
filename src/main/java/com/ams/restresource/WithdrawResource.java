/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.repository.AccountRepository;
import com.ams.repository.ClientRepository;
import com.ams.request.AccountRequest;
import com.ams.request.TransactionRequest;
import com.ams.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avni
 */
@RequestScoped
@Path("/withdraw")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WithdrawResource {
    
    @Inject
    TransactionService transactionService;

    @Inject
    ClientRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @POST
    public Response withdraw(TransactionRequest withdrawRequest) {

        Long userId = withdrawRequest.getUserId();
        List<AccountRequest> accountRequests = withdrawRequest.getAccountList();

        Client selectedUser = userRepository.findById(userId);
        List<Account> accountList = new ArrayList<>();

        for (AccountRequest accountRequest : accountRequests) {
            String accountNo = accountRequest.getAccountNo();
            Account account = accountRepository.findByAccNo(accountNo);
            
            if (account != null) {
                account.setAmount(accountRequest.getAmount());
                accountList.add(account);
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Account not found")
                    .build();
            }
        }

        boolean withdrawSuccessful = transactionService.performWithdrawal(selectedUser, accountList);

        if (withdrawSuccessful) {
            return Response.ok("Amount successfully withdrawn").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Withdrawal failed")
                    .build();
        }
    }
    
}
