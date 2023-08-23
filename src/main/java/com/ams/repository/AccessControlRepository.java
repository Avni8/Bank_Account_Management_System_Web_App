/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.ActionType;
import com.ams.model.Permission;
import com.ams.model.ResourceType;
import com.ams.model.UserRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author avni
 */
@Stateless
public class AccessControlRepository extends AbstractRepository<Permission> {

    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public AccessControlRepository() {
        super(Permission.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
    

    public boolean isPermissionAllowed(UserRole role, ResourceType resource, ActionType action) {

        String jpql = "SELECT p.allow FROM Permission p WHERE p.role = :role "
                + "AND p.resource = :resource AND p.action = :action";

        try {
            Boolean allow = entityManager.createQuery(jpql, Boolean.class)
                    .setParameter("role", role)
                    .setParameter("resource", resource)
                    .setParameter("action", action)
                    .getSingleResult();

            return allow != null && allow;
        } catch (NoResultException e) {
            return false;
        }
    }

}
