/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author avni
 */

@Entity
@Table(name="user")
public class User extends AbstractEntity {
    
//    private Long id;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String email; 
    private String product_type;
    private String username;
    private String password;
    
//    public User(Long id, String name, String address, String contact, String dob, String email, 
//                    String product_type, String username, String password) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.contact = contact;
//        this.dob = dob;
//        this.email = email;
//        this.product_type = product_type;
//        this.username = username;
//        this.password = password;
//    }
    
//    public Long getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getContact() {
        return contact;
    }
    
    public String getDob() {
        return dob;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getProduct_type() {
        return product_type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
     public String getTableName(){
        return "user";
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
