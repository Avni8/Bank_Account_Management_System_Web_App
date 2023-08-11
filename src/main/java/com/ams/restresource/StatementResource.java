/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.User;
import com.ams.request.StatementRequest;
import com.ams.repository.AccountRepository;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.BalanceView;
import com.ams.repository.UserRepository;
import com.ams.service.StatementService;
import com.ams.response.StatementResponse;
import java.util.Date;
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
@Path("/viewStatement")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatementResource {

    @Inject
    StatementService statementService;

    @Inject
    UserRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @POST
    public Response viewStatement(StatementRequest statementRequest) {

        Long userId = statementRequest.getUserId();
        Date fromDate = statementRequest.getFromDate();
        Date toDate = statementRequest.getToDate();
        String accNo = statementRequest.getAccNo();

        User user = userRepository.findById(userId);
        Account account = accountRepository.findByAccNo(accNo);

        if (account != null) {
            BalanceView balanceView = new BalanceView();
            balanceView.setAccount(account);
            balanceView.setFromDate(fromDate);
            balanceView.setToDate(toDate);
            List<AccountTransactionDetails> transactionDetails
                    = statementService.loadTransactionDetails(balanceView, account);
            balanceView = statementService.loadOpeningBalance(account, balanceView);

            if (transactionDetails != null && balanceView != null) {

                StatementResponse response = new StatementResponse();
                
                response.setTransactionDetails(transactionDetails);
                response.setBalanceUptoFromDate(balanceView.getBalanceUptoFromDate());
                response.setLatestBalance(account.getBalance());
                
                return Response.ok(response).build();

            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No transactions found")
                        .build();
            }

        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Account not found")
                    .build();
        }

    }

}
