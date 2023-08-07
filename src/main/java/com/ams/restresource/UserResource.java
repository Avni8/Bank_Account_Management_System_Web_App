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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") long id) {
        return userRepository.findById(id);
    }

    @POST
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") long id) {
        userRepository.delete(id);
    }
}
