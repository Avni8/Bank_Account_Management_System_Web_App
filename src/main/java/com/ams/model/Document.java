/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.nio.file.Paths;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author avni
 */
@Entity
@Table(name = "file")
public class Document extends AbstractEntity{
    
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "documet_size")
    private long documentSize;
    @Column(name = "document_path")
    private String documentPath;
    @Column(name = "user")
    private String client;
    

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(long documentSize) {
        this.documentSize = documentSize;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
    
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
