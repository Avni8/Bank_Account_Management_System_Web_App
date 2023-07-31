/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

//    public List<User> getUserById(Long id) {
//        List<User> userList = null;
//        try {
//            Query query = entityManager().createQuery("Select u from User u where u.id=:id", User.class);
//            query.setParameter("id", id);
//            userList = query.getResultList();
//            return userList;
//
//        } catch (Exception r) {
//
//        }
//        return userList;
//    }
    
    
}
