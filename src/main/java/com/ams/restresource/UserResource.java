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
import com.ams.restresource.ApiResponse;
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

    @GET
    public Response getAllUsers() {
        List<User> users = userRepository.findAll();
        ApiResponse<List<User>> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", users);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            ApiResponse<User> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", user);
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } else {
            ApiResponse<User> response = new ApiResponse<>(Response.Status.NOT_FOUND.getStatusCode(), "User not found", null);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }
    }

    @POST
    public Response createUser(User user) {
        User createdUser = userRepository.save(user);
        ApiResponse<User> response = new ApiResponse<>(Response.Status.CREATED.getStatusCode(), "User sucessfully created", createdUser);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User user) {
        userRepository.update(user);
        ApiResponse<User> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "User sucessfully updated", user);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        userRepository.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "User successfully deleted", null);
        return Response.status(Response.Status.OK)
                       .entity(response)
                       .build();
    }
}
