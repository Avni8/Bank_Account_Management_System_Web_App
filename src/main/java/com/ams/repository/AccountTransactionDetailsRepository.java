/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.Account;
import com.ams.model.AccountTransactionDetails;
import com.ams.model.BalanceView;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public List<AccountTransactionDetails> getTransactionsByAccount(Account account, Date fromDate,
            Date toDate) {
        List<AccountTransactionDetails> transactionDetails
                = entityManager.createQuery("SELECT a FROM AccountTransactionDetails"
                        + " a WHERE a.account.id = :acId"
                        + " AND a.date BETWEEN :startDate AND :endDate", AccountTransactionDetails.class)
                        .setParameter("acId", account.getId())
                        .setParameter("startDate", fromDate)
                        .setParameter("endDate", toDate)
                        .getResultList();

        return transactionDetails;
    }

    public Double getOpeningBalance(Account account, Date date) {
        String jpql = "SELECT COALESCE(SUM(atd.creditAmount), 0) - "
                + "COALESCE(SUM(atd.debitAmount), 0) + :balance "
                + "FROM AccountTransactionDetails atd "
                + "WHERE atd.account = :account AND atd.date < :date";
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        query.setParameter("account", account);
        query.setParameter("date", date);
        query.setParameter("balance", account.getInitialBalance());
        Double openingBalance = query.getSingleResult();
        return openingBalance;
    }

    public BalanceView getOpeningBalance(BalanceView balanceView) {

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(balanceView.getFromDate()); 
//        int daysToDecrement = -1;
//        cal.add(Calendar.DATE, daysToDecrement);
//        Date actualFromDate = cal.getTime(); 
//        Double balanceUptoFromDate = getOpeningBalance(balanceView.getAccount(),
//                actualFromDate);
        
        Double balanceUptoFromDate = getOpeningBalance(balanceView.getAccount(),
                balanceView.getFromDate());
        Double balanceUptoToDate = getOpeningBalance(balanceView.getAccount(),
                balanceView.getToDate());
        balanceView.setBalanceUptoFromDate(balanceUptoFromDate);
        balanceView.setBalanceUptoToDate(balanceUptoToDate);
        return balanceView;
    }
}
