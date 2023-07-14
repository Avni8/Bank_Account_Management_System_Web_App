/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.AccountTransactionDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author avni
 */
@Stateless
public class AccountTransactionDetailsRepository extends AbstractRepository<AccountTransactionDetails> {
    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager; 

    public AccountTransactionDetailsRepository() {
        super(AccountTransactionDetails.class);
    }
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
