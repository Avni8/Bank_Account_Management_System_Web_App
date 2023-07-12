/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author avni
 */
@Stateless
public class ProductRepository extends AbstractRepository<Product>  {
    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;
    
    public ProductRepository() {
        super(Product.class);
    }
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
