/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return Objects.equals(this.getId(), product.getId());
    }

}
