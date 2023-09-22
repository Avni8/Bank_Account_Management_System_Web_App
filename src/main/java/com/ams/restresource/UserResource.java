/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.controller.AccessControlInterceptor;
import com.ams.controller.RequiredPermission;
import com.ams.model.ActionType;
import com.ams.model.ResourceType;
import com.ams.model.User;
import com.ams.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource implements Serializable {

    @Inject
    UserRepository userRepository;
    
    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.USER)
    @POST
    public Response createUser(User user) throws JsonProcessingException {
        userRepository.save(user);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);

        return RestResponse.responseBuilder("true", "201", "User created successfully", str);
    }

    @RequiredPermission(action = ActionType.READ, resource = ResourceType.USER)
    @GET
    public Response getAllUsers() throws JsonProcessingException {
        List<User> userList = userRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(userList);
        return RestResponse.responseBuilder("true", "200", "List of users", str);
    }

    @RequiredPermission(action = ActionType.READ, resource = ResourceType.USER)
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) throws JsonProcessingException {
        User user = userRepository.findById(id);
        if (user == null) {

            return RestResponse.responseBuilder("false", "404", " user with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        return RestResponse.responseBuilder("true", "200", "user with id " + id + " found", str);
    }

    @RequiredPermission(action = ActionType.UPDATE, resource = ResourceType.USER)
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User user) throws JsonProcessingException {
        User usr = userRepository.findById(id);
        if (usr == null) {
            return RestResponse.responseBuilder("false", "404", " user with id " + id + " not found", null);
        }
        if (!user.getId().equals(usr.getId())) {
            return RestResponse.responseBuilder("false", "404", " user id mismatch", null);
        }
        userRepository.update(user);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);

        return RestResponse.responseBuilder("true", "200", "user updated successfully", str);
    }

    @RequiredPermission(action = ActionType.DELETE, resource = ResourceType.USER)
    @DELETE
    @Path("/{id}")
    public Response deleteCLient(@PathParam("id") long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return RestResponse.responseBuilder("false", "404", " user with id " + id + " not found", null);
        }
        userRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "user with id " + id + " deleted successfully", null);
    }

}
