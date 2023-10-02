/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Document;
import com.ams.repository.DocumentRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import static java.rmi.server.RemoteServer.getLog;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("downloadController")
public class DownloadController extends AbstractMessageController {

    private DefaultStreamedContent download;

    @Inject
    DocumentRepository fileRepository;

    public DefaultStreamedContent getDownload() throws Exception {
        System.out.println("GET = " + download.getName());
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public void prepDownload(Document file) throws Exception {
        
        File downloadFile = new File(file.getDocumentPath());
        InputStream input = new FileInputStream(downloadFile);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(downloadFile.getName()), downloadFile.getName()));
        System.out.println("PREP = " + download.getName());

    }

}
