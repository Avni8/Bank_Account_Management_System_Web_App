/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.restresource;

import com.ams.model.Product;
import com.ams.repository.ProductRepository;
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
public class ProductResource implements Serializable{
    
    @Inject
    ProductRepository productRepository;
    
    @GET
    public Response getAllProducts() {
        List<Product> products = productRepository.findAll();
        ApiResponse<List<Product>> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", products);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            ApiResponse<Product> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Success", product);
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } else {
            ApiResponse<Product> response = new ApiResponse<>(Response.Status.NOT_FOUND.getStatusCode(), "Product not found", null);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }
    }

    @POST
    public Response createProduct(Product product) {
        Product createdProduct = productRepository.save(product);
        ApiResponse<Product> response = new ApiResponse<>(Response.Status.CREATED.getStatusCode(), "Product sucessfully created", createdProduct);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") long id, Product product) {
        productRepository.update(product);
        ApiResponse<Product> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Product sucessfully updated", product);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        productRepository.delete(id);
        ApiResponse<Product> response = new ApiResponse<>(Response.Status.OK.getStatusCode(), "Product successfully deleted", null);
        return Response.status(Response.Status.OK)
                       .entity(response)
                       .build();
    }
    
}
