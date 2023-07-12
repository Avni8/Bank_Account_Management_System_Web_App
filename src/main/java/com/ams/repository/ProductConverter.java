/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import com.ams.model.Product;
/**
 *
 * @author avni
 */
@FacesConverter(value="productConverter", forClass= Product.class)
public class ProductConverter extends GenericConverter {
    
    @Inject
    private ProductRepository repo;

    @Override
    protected AbstractRepository getRepo() {
        return repo;
    }
}
