/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import javax.faces.convert.FacesConverter;
import com.ams.model.User;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@FacesConverter(value = "userConverter", forClass = User.class)
public class UserConverter extends GenericConverter {

    @Inject
    private UserRepository repo;
    
    @Override
    protected AbstractRepository getRepo() {
        
        return repo;
    }
}
