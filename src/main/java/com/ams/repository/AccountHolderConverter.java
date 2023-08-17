/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import javax.faces.convert.FacesConverter;
import com.ams.model.AccountHolder;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@FacesConverter(value = "accountHolderConverter", forClass = AccountHolder.class)
public class AccountHolderConverter extends GenericConverter {

    @Inject
    private AccountHolderRepository repo;
    
    @Override
    protected AbstractRepository getRepo() {
        
        return repo;
    }
}
