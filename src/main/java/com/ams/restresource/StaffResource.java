/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Staff;
import com.ams.repository.StaffRepository;
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
@Path("/staff")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffResource implements Serializable{
    
    @Inject
    StaffRepository staffRepository;

    @GET
    public Response getAllStaffs() {
        List<Staff> staffs = staffRepository.findAll();
        ApiResponse<List<Staff>> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", staffs);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getStaffById(@PathParam("id") long id) {
        Staff staff = staffRepository.findById(id);
        if (staff != null) {
            ApiResponse<Staff> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", staff);
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } else {
            ApiResponse<Staff> response = new ApiResponse<>(Response.Status.NOT_FOUND.getStatusCode(), "Staff not found", null);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }
    }

    @POST
    public Response createStaff(Staff staff) {
        Staff createdStaff = staffRepository.save(staff);
        ApiResponse<Staff> response = new ApiResponse<>(Response.Status.CREATED.getStatusCode(), "Staff sucessfully created", createdStaff);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStaff(@PathParam("id") long id, Staff staff) {
        staffRepository.update(staff);
        ApiResponse<Staff> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Staff sucessfully updated", staff);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        staffRepository.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Staff successfully deleted", null);
        return Response.status(Response.Status.OK)
                       .entity(response)
                       .build();
    }
}
