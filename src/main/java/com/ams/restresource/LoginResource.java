/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Staff;
import com.ams.request.LoginRequest;
import com.ams.service.LoginService;
import com.ams.jwt.JwtUtils;
import com.ams.model.User;
import com.ams.model.UserRole;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService loginService;

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    public Response login(LoginRequest loginRequest) {

        JwtUtils jwtUtils = new JwtUtils();

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User returnedUser = loginService.login(username, password);

        if (returnedUser != null) {

            httpServletRequest.getSession().setAttribute("loggedInUser", returnedUser);
            String jwtToken = jwtUtils.generateJwtToken(returnedUser.getUsername());

            if (returnedUser.getRole() == UserRole.CLIENT) {
                return RestResponse.responseBuilder(
                        "true", "200", "Client login Successful", jwtToken);
            } else if (returnedUser.getRole() == UserRole.STAFF) {
                return RestResponse.responseBuilder(
                        "true", "200", "Staff Login Successful", jwtToken);
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Login failed. Invalid credentials.")
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Login failed. Invalid credentials.")
                .build();
    }
}
