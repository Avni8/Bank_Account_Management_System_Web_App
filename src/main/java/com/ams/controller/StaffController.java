/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;
import com.ams.model.AbstractEntity;
import com.ams.repository.StaffRepository;
import com.ams.model.Staff;
import com.ams.repository.AbstractRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("staffController")
public class StaffController extends AbstractController{
    
    private Staff staff;
    private List<Staff> staffList;

    @Inject
    private StaffRepository staffRepository;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
    
    @PostConstruct
    public void init() {
        staff = new Staff();
        loadData();
    }

    public void loadData() {
        staffList = staffRepository.findAll();
    }

    public void beforeCreate() {
        staff = new Staff();
    }

    public void beforeUpdate(Staff staff) {
        this.staff = staff;
    }

//    public void createUpdate() {
//        if (staff.getId() == null) {
//            staffRepository.save(staff);
//        } else {
//            staffRepository.update(staff);
//        }
//        loadData();
//    }

    public void delete(Staff staff) {
        staffRepository.delete(staff.getId());
        loadData();
    } 
    
    @Override
    public AbstractRepository getRepository(){
        return staffRepository;
    }
    
    @Override
    public AbstractEntity getEntity(){
        return staff;
    }
}
