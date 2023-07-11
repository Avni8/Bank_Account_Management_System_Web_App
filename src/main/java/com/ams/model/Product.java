/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author avni
 */
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {
    
    @Column(name = "product_type")
    private String productType;

    public String getProduct_type() {
        return productType;
    }

    public void setProduct_type(String productType) {
        this.productType = productType;
    }
}
