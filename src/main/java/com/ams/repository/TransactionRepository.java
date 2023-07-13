/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.Transaction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author avni
 */
@Stateless
public class TransactionRepository extends AbstractRepository<Transaction> {
    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public TransactionRepository() {
        super(Transaction.class);
    }
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
