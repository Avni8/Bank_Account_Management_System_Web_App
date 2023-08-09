/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Staff;
import com.ams.repository.StaffRepository;
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
@Path("/staff")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffResource implements Serializable {

    @Inject
    StaffRepository staffRepository;

    @POST
    public Response createStaff(Staff staff) throws JsonProcessingException {
        staffRepository.save(staff);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(staff);

        return RestResponse.responseBuilder("true", "201", "Staff created successfully", str);
    }

    @GET
    public Response getAllStaffs() throws JsonProcessingException {
        List<Staff> staffList = staffRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(staffList);
        return RestResponse.responseBuilder("true", "200", "List of staffs", str);
    }

    @GET
    @Path("/{id}")
    public Response getStaffById(@PathParam("id") long id) throws JsonProcessingException {
        Staff staff = staffRepository.findById(id);
        if (staff == null) {

            return RestResponse.responseBuilder("false", "404", " Staff with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(staff);
        return RestResponse.responseBuilder("true", "200", "Staff with id " + id + " found", str);

    }

    @PUT
    @Path("/{id}")
    public Response updateStaff(@PathParam("id") long id, Staff staff) throws JsonProcessingException {
        Staff sf = staffRepository.findById(id);
        if (sf == null) {
            return RestResponse.responseBuilder("false", "404", " Staff with id " + id + " not found", null);
        }
        if (!staff.getId().equals(sf.getId())) {
            return RestResponse.responseBuilder("false", "404", " Staff id mismatch", null);
        }
        staffRepository.update(staff);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(staff);

        return RestResponse.responseBuilder("true", "200", " Staff updated successfully", str);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStaff(@PathParam("id") long id) throws JsonProcessingException {
        Staff sf = staffRepository.findById(id);
        if (sf == null) {
            return RestResponse.responseBuilder("false", "404", " Staff with id " + id + " not found", null);
        }
        staffRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "Staff with id " + id + " deleted successfully", null);

    }
}
