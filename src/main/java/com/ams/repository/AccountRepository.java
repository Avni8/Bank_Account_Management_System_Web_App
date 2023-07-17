/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.Account;
import com.ams.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author avni
 */
@Stateless
public class AccountRepository extends AbstractRepository<Account>{
    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;
    
    public AccountRepository() {
        super(Account.class);
    }
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
    
    public List<Account> getAccountsByUser(User user) {
    // Use appropriate database query or ORM query to fetch accounts by user
    // Return the list of accounts
    return entityManager.createQuery("SELECT a FROM Account a WHERE a.user = :user", Account.class)
            .setParameter("user", user)
            .getResultList();
}

    
}
