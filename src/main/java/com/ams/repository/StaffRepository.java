/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import com.ams.model.Staff;
import com.ams.model.User;
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
public class StaffRepository extends AbstractRepository<Staff>{
    
    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;
    
    public StaffRepository() {
        super(Staff.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
    
    public Staff findByUsername(String username) {
        TypedQuery<Staff> query = entityManager.createQuery(
                "SELECT u FROM Staff u WHERE u.username = :username", Staff.class
        ).setParameter("username", username);

        List<Staff> staffList = query.getResultList();
        return staffList.isEmpty() ? null : staffList.get(0);
        
    }
    
    public Staff getStaffByUser(User user) {

        String jpql = "SELECT c FROM Staff c WHERE c.user = :user";
        TypedQuery<Staff> query = entityManager.createQuery(jpql, Staff.class);
        query.setParameter("user", user);
        List<Staff> staffs = query.getResultList();

        if (!staffs.isEmpty()) {
            return staffs.get(0);
        } else {
            return null;
        }
    }
}
