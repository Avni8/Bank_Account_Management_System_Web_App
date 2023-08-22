/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.Client;
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
public class ClientRepository extends AbstractRepository<Client> {

    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public ClientRepository() {
        super(Client.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

//    public List<User> getUserById(Long id) {
//        List<User> userList = null;
//        try {
//            Query query = entityManager().createQuery("Select u from Client u where u.id=:id", Client.class);
//            query.setParameter("id", id);
//            userList = query.getResultList();
//            return userList;
//
//        } catch (Exception r) {
//
//        }
//        return userList;
//    }
    
    public Client findByUsername(String username) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT u FROM Client u WHERE u.username = :username", Client.class
        ).setParameter("username", username);

        List<Client> userList = query.getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }

    public Client getClientByUser(User user) {

        String jpql = "SELECT c FROM Client c WHERE c.user = :user";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("user", user);
        List<Client> clients = query.getResultList();

        if (!clients.isEmpty()) {
            return clients.get(0);
        } else {
            return null;
        }
    }

}
