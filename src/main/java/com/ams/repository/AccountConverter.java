/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.Account;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@FacesConverter(value = "accountConverter", forClass = Account.class)
public class AccountConverter extends GenericConverter{
    
    @Inject
    private AccountRepository repo;

    @Override
    protected AbstractRepository getRepo() {
        return repo;
    }
    
}
