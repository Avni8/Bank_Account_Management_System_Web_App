/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import com.ams.model.Client;

/**
 *
 * @author avni
 */
@FacesConverter(value = "clientConverter", forClass = Client.class)
public class ClientConverter extends GenericConverter {

    @Inject
    private ClientRepository repo;

    @Override
    protected AbstractRepository getRepo() {
        return repo;
    }
}
