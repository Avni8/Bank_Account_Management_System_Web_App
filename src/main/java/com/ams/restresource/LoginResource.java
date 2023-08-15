/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Staff;
import com.ams.request.LoginRequest;
import com.ams.service.LoginService;
import com.ams.jwt.GenerateJWTToken;
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
    
    private GenerateJWTToken generateJWTToken;
    
    @POST
    public Response login(LoginRequest loginRequest) {

        generateJWTToken = new GenerateJWTToken();
        
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Staff returnedStaff = loginService.login(username, password);

        if (returnedStaff != null) {

            httpServletRequest.getSession().setAttribute("loggedInStaff", returnedStaff);
            String jwtToken;
            jwtToken = generateJWTToken.generateToken(returnedStaff);
            return RestResponse.responseBuilder(
                    "true", "200", "Login Successful",jwtToken);
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Login failed. Invalid credentials.")
                    .build();
        }
    }
}
