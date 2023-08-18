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
import java.util.Date;
import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author avni
 */
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {

    private String name;
    private String address;
    private String contact;
    
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    private String email;
//    private String username;
//    private String password;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }


//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
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

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
//    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client otherUser = (Client) obj;
        return Objects.equals(this.getId(), otherUser.getId());
    } 
}
