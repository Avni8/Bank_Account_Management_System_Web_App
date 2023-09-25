/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.service.TransactionService;
import com.ams.request.FundTransferRequest;
import com.ams.repository.AccountRepository;
import com.ams.repository.ClientRepository;
import com.ams.controller.UserBean;
import com.ams.model.UserRole;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
    ClientRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    @Context
    HttpServletRequest httpServletRequest;

    @Inject
    UserBean userBean;

    @POST
    public Response fundTransfer(FundTransferRequest transferRequest) {

        boolean allow = false;
        Long fromUserId = transferRequest.getFromUserId();
        Long toUserId = transferRequest.getToUserId();
        String sourceAccountNumber = transferRequest.getSourceAccountNumber();
        String destinationAccountNumber = transferRequest.getDestinationAccountNumber();
        Double transferAmount = transferRequest.getTransferAmount();
        Client fromUser = userRepository.findById(fromUserId);
        Client toUser = userRepository.findById(toUserId);

        UserRole userRole = (UserRole) httpServletRequest.getSession().getAttribute("userRole");

        if (userRole.equals(UserRole.CLIENT)) {

            Client loggedInClient = userBean.getCurrentClient();
            allow = Objects.equals(fromUser, loggedInClient);

        }

        if (userRole == UserRole.STAFF || allow == true) {

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
        else{
            return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Access Denied")
                        .build();
        }

    }
}
