/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.repository.ClientRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.ams.model.Client;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;

/**
 *
 * @author avni
 */
@RequestScoped
@Path("/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource implements Serializable {

    @Inject
    ClientRepository clientRepository;

    @POST
    public Response createClient(Client client) throws JsonProcessingException {
        clientRepository.save(client);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(client);

        return RestResponse.responseBuilder("true", "201", "Client created successfully", str);
    }

    @GET
    public Response getAllClients() throws JsonProcessingException {
        List<Client> clientList = clientRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(clientList);
        return RestResponse.responseBuilder("true", "200", "List of clients", str);
    }

    @GET
    @Path("/{id}")
    public Response getClientById(@PathParam("id") long id) throws JsonProcessingException {
        Client client = clientRepository.findById(id);
        if (client == null) {

            return RestResponse.responseBuilder("false", "404", " client with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(client);
        return RestResponse.responseBuilder("true", "200", "client with id " + id + " found", str);
    }

    @PUT
    @Path("/{id}")
    public Response updateClient(@PathParam("id") long id, Client client) throws JsonProcessingException {
        Client usr = clientRepository.findById(id);
        if (usr == null) {
            return RestResponse.responseBuilder("false", "404", " client with id " + id + " not found", null);
        }
        if (!client.getId().equals(usr.getId())) {
            return RestResponse.responseBuilder("false", "404", " client id mismatch", null);
        }
        clientRepository.update(client);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(client);

        return RestResponse.responseBuilder("true", "200", "client updated successfully", str);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCLient(@PathParam("id") long id) {
        Client client = clientRepository.findById(id);
        if (client == null) {
            return RestResponse.responseBuilder("false", "404", " client with id " + id + " not found", null);
        }
        clientRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "client with id " + id + " deleted successfully", null);
    }
}
