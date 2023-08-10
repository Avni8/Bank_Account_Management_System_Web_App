/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.User;
import com.ams.service.TransactionService;
import com.ams.request.FundTransferRequest;
import com.ams.repository.AccountRepository;
import com.ams.repository.UserRepository;
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
@Path("/fundtransfer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FundTransferResource {

    @Inject
    TransactionService transactionService;

    @Inject
    UserRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @POST
    public Response fundTransfer(FundTransferRequest transferRequest) {

        Long fromUserId = transferRequest.getFromUserId();
        Long toUserId = transferRequest.getToUserId();
        String sourceAccountNumber = transferRequest.getSourceAccountNumber();
        String destinationAccountNumber = transferRequest.getDestinationAccountNumber();
        Double transferAmount = transferRequest.getTransferAmount();

        User fromUser = userRepository.findById(fromUserId);
        User toUser = userRepository.findById(toUserId);
        Account sourceAccount = accountRepository.findByAccNo(sourceAccountNumber);
        Account destinationAccount = accountRepository.findByAccNo(destinationAccountNumber);

        boolean transferSuccessful = transactionService.performFundTransfer(
                fromUser, toUser, sourceAccount, destinationAccount, transferAmount
        );
        
         if (transferSuccessful) {
            return Response.ok("Fund Transfer Successful").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Fund Transfer Failed")
                    .build();
        }
    }
}
