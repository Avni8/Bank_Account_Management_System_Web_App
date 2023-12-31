/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.controller.UserBean;
import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.request.StatementRequest;
import com.ams.repository.AccountRepository;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.BalanceView;
import com.ams.model.UserRole;
import com.ams.repository.ClientRepository;
import com.ams.service.StatementService;
import com.ams.response.StatementResponse;
import java.util.Date;
import java.util.List;
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
@Path("/viewStatement")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatementResource {

    @Inject
    StatementService statementService;

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
    public Response viewStatement(StatementRequest statementRequest) {

        boolean allow = false;
        Long userId = statementRequest.getUserId();
        Date fromDate = statementRequest.getFromDate();
        Date toDate = statementRequest.getToDate();
        String accNo = statementRequest.getAccNo();

        Client user = userRepository.findById(userId);
        UserRole userRole = (UserRole) httpServletRequest.getSession().getAttribute("userRole");
        Client loggedInUser = userBean.getCurrentClient();

        if (userRole == UserRole.CLIENT) {
            allow = Objects.equals(user, loggedInUser);
        }

        if (userRole == UserRole.STAFF || allow == true) {

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
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Access Denied")
                    .build();
        }

    }

}
