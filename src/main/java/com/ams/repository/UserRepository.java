/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.User;
import java.util.List;
import javax.ejb.Stateless;
import com.ams.model.UserRole;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avni
 */
@Stateless
public class UserRepository extends AbstractRepository<User> {

    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public UserRepository() {
        super(User.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class
        ).setParameter("username", username);

        List<User> userList = query.getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }
   

    public List<User> getUserByRoleName(String role) {

        String jpql = "SELECT ah FROM User ah WHERE ah.role = :role";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("roleName", role);
        return query.getResultList();
    }

    public List<User> getUserWithNoClientModel() {
        String jpql = "SELECT u FROM User u "
                + "WHERE u.role = :role "
                + "AND NOT EXISTS (SELECT c FROM Client c WHERE c.user = u)";

        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("role", UserRole.CLIENT); 

        return query.getResultList();
    }

    public List<User> getUserWithNoStaffModel() {
        String jpql = "SELECT u FROM User u "
                + "WHERE u.role = :role "
                + "AND NOT EXISTS (SELECT c FROM Staff c WHERE c.user = u)";

        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("role", UserRole.STAFF); 

        return query.getResultList();
    }

}
