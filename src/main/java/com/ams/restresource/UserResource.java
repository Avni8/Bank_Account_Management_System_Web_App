/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.repository.UserRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.ams.model.User;
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
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource implements Serializable {

    @Inject
    UserRepository userRepository;

    @POST
    public Response createUser(User user) throws JsonProcessingException {
        userRepository.save(user);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);

        return RestResponse.responseBuilder("true", "201", "User created successfully", str);
    }

    @GET
    public Response getAllUsers() throws JsonProcessingException {
        List<User> userList = userRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(userList);
        return RestResponse.responseBuilder("true", "200", "List of users", str);
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) throws JsonProcessingException {
        User user = userRepository.findById(id);
        if (user == null) {

            return RestResponse.responseBuilder("false", "404", " User with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        return RestResponse.responseBuilder("true", "200", "User with id " + id + " found", str);
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User user) throws JsonProcessingException {
        User usr = userRepository.findById(id);
        if (usr == null) {
            return RestResponse.responseBuilder("false", "404", " User with id " + id + " not found", null);
        }
        if (!user.getId().equals(usr.getId())) {
            return RestResponse.responseBuilder("false", "404", " User id mismatch", null);
        }
        userRepository.update(user);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);

        return RestResponse.responseBuilder("true", "200", "User updated successfully", str);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return RestResponse.responseBuilder("false", "404", " User with id " + id + " not found", null);
        }
        userRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "User with id " + id + " deleted successfully", null);
    }
}
