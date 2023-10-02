/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.jwt;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

/**
 *
 * @author avni
 */
@Provider
//@Priority(Priorities.AUTHENTICATION)
@Priority(2)
public class JWTAuthorizationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String requestPath = requestContext.getUriInfo().getPath();
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (requestPath.equals("/login")) {
            return;
        } else {

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring("Bearer".length()).trim();
                try {
                    String userId = JwtUtils.verifyToken(token);
                    if (userId != null) {
                        return;
                    } else {
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                } catch (Exception e) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }
            }
        }
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

}

