/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.UserRole;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@FacesConverter(value = "roleConverter", forClass = UserRole.class)
public class RoleConverter extends GenericConverter{
    
     @Inject
    private UserRoleRepository repo;

    @Override
    protected AbstractRepository getRepo() {
        return repo;
    }
    
}
