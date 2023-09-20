/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Account;
import com.ams.model.Client;
import com.ams.model.UserRole;
import com.ams.model.User;
import com.ams.repository.AccountRepository;
import com.ams.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("/clientAccount")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientAccount {

    @Inject
    AccountRepository accountRepository;

    @Inject
    ClientRepository clientRepository;

    @Context
    private HttpServletRequest httpServletRequest;

    @GET
    public Response getAccountsByClient() throws JsonProcessingException {

        User loggedInUser = (User) httpServletRequest.getSession().getAttribute("loggedInUser");
        String string="";

        if (loggedInUser.getRole().equals(UserRole.CLIENT)) {

            Client client = clientRepository.getClientByUser(loggedInUser);

            List<Account> accountList = accountRepository.getAccountsByUser(client);

            ObjectMapper mapper = new ObjectMapper();
            string = mapper.writeValueAsString(accountList);

        }
            return RestResponse.responseBuilder("true", "200", "List of accounts", string);

    }

}
