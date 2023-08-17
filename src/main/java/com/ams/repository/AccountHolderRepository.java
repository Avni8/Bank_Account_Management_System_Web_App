/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.AccountHolder;
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
public class AccountHolderRepository extends AbstractRepository<AccountHolder> {

    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;

    public AccountHolderRepository() {
        super(AccountHolder.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

    public AccountHolder findByUsername(String username) {
        TypedQuery<AccountHolder> query = entityManager.createQuery(
                "SELECT u FROM AccountHolder u WHERE u.username = :username", AccountHolder.class
        ).setParameter("username", username);

        List<AccountHolder> accountList = query.getResultList();
        return accountList.isEmpty() ? null : accountList.get(0);
    }

    public List<AccountHolder> getAccountHoldersByRoleName(String roleName) {

        String jpql = "SELECT ah FROM AccountHolder ah WHERE ah.role.roleName = :roleName";
        TypedQuery<AccountHolder> query = entityManager.createQuery(jpql, AccountHolder.class);
        query.setParameter("roleName", roleName);
        return query.getResultList();
    }
}
