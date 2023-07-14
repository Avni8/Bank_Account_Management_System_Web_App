/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.AccountMIS;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author avni
 */
@FacesConverter(value = "accountMISConverter", forClass = AccountMIS.class)
public class AccountMISConverter extends GenericConverter {
    @Inject
    private UserRepository repo;

    @Override
    protected AbstractRepository getRepo() {
        return repo;
    }
}
