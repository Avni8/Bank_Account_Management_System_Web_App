/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import javax.ejb.Stateless;
import com.ams.model.Document;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author avni
 */
@Stateless
public class DocumentRepository extends AbstractRepository<Document>{

    @PersistenceContext(name = "AMS")
    private EntityManager entityManager;
    
    public DocumentRepository() {
        super(Document.class);
    }
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
