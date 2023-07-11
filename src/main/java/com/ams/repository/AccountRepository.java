/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.Account;
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
}
