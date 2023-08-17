/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.model.UserRole;
import com.ams.repository.AbstractRepository;
import com.ams.repository.UserRoleRepository;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("roleController")
public class UserRoleController extends AbstractController {

    private UserRole userRole;
    private List<UserRole> roleList;

    @Inject
    UserRoleRepository roleRepository;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public void loadData() {
        roleList = roleRepository.findAll();
    }

    @Override
    public AbstractRepository getRepository() {
        return roleRepository;
    }

    @Override
    public AbstractEntity getEntity() {
        return userRole;
    }
}
