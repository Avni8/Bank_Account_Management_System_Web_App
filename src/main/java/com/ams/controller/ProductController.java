/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.ActionType;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.ams.model.Product;
import com.ams.model.ResourceType;
import com.ams.repository.AbstractRepository;
import com.ams.repository.ProductRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("productController")
public class ProductController extends AbstractController{
    
    private Product product;
    private List<Product> productList;

    @Inject
    private ProductRepository productRepository;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    @PostConstruct
    public void init() {
        product = new Product();
        loadData();
    }

    @Override
    public void loadData() {
        productList = productRepository.findAll();
    }
    
    @Override
    @RequiredPermission(action = ActionType.WRITE, resource = ResourceType.PRODUCT)
    public void createUpdate(){
        super.createUpdate();
        
    }
    
    @RequiredPermission(action = ActionType.DELETE, resource = ResourceType.PRODUCT)
    public void delete(Product product){
        productRepository.delete(product.getId());
        loadData();
    }
    
    

    public void beforeCreate() {
        product = new Product();
    }

    public void beforeUpdate(Product product) {
        this.product = product;
    }
    
    @Override
    public AbstractRepository getRepository() {
        return productRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return product;
    }
    
}
