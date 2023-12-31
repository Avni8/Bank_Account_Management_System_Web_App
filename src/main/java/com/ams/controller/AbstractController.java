/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.AbstractEntity;
import com.ams.repository.AbstractRepository;

/**
 *
 * @author avni
 */
public abstract class AbstractController extends AbstractMessageController {

    
    public void createUpdate() {
        if (getEntity().getId() == null) {
            getRepository().save(getEntity());
            super.infoMessage("Created Successfully");
        } else {
            getRepository().update(getEntity());
            super.infoMessage("Updated Successfully");
        }
        loadData();
    }

    public abstract void loadData();

    public abstract AbstractRepository getRepository();

    public abstract AbstractEntity getEntity();

}
