/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.UserRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author avni
 */
@Stateless
public class UserRoleRepository extends AbstractRepository<UserRole> {

    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public UserRoleRepository() {
        super(UserRole.class);
    }

    protected EntityManager entityManager() {
        return entityManager;
    }
}
