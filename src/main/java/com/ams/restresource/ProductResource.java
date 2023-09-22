/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.controller.RequiredPermission;
import com.ams.model.ActionType;
import com.ams.model.Product;
import com.ams.model.ResourceType;
import com.ams.repository.ProductRepository;
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
@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource implements Serializable {

    @Inject
    ProductRepository productRepository;

    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.PRODUCT)
    @POST
    public Response createProduct(Product product) throws JsonProcessingException {
        productRepository.save(product);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(product);

        return RestResponse.responseBuilder("true", "201", "Product created successfully", str);

    }

    @RequiredPermission(action = ActionType.READ, resource = ResourceType.PRODUCT)
    @GET
    public Response getAllProducts() throws JsonProcessingException {
        List<Product> productList = productRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(productList);
        return RestResponse.responseBuilder("true", "200", "List of products", str);
    }

    @RequiredPermission(action = ActionType.READ, resource = ResourceType.PRODUCT)
    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") long id) throws JsonProcessingException {
        Product product = productRepository.findById(id);
        if (product == null) {

            return RestResponse.responseBuilder("false", "404", " User with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(product);
        return RestResponse.responseBuilder("true", "200", "Product with id " + id + " found", str);

    }

    @RequiredPermission(action = ActionType.UPDATE, resource = ResourceType.PRODUCT)
    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") long id, Product product) throws JsonProcessingException {
        Product pr = productRepository.findById(id);
        if (pr == null) {
            return RestResponse.responseBuilder("false", "404", " Product with id " + id + " not found", null);
        }
        if (!product.getId().equals(pr.getId())) {
            return RestResponse.responseBuilder("false", "404", "Product id mismatch", null);
        }
        productRepository.update(product);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(product);

        return RestResponse.responseBuilder("true", "200", "Product updated successfully", str);

    }

    @RequiredPermission(action = ActionType.DELETE, resource = ResourceType.PRODUCT)
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return RestResponse.responseBuilder("false", "404", " Product with id " + id + " not found", null);
        }
        productRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "Product with id " + id + " deleted successfully", null);

    }

}
