/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.controller;

import com.ams.model.Document;
import com.ams.repository.DocumentRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author avni
 */
@ViewScoped
@Named("uploadController")
public class UploadController extends AbstractMessageController {

    private Document document;
    private UploadedFile file;
    private List<Document> documentList;
   

    private static final String UPLOADS_DIRECTORY = "/home/avni/uploads";

    @Inject
    private DocumentRepository documentRepository;
    @Inject
    private UserBean userBean;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void fileUploadListener(FileUploadEvent e) {

        this.file = e.getFile();

        String fileName = file.getFileName();
        String fileType = file.getContentType();
        long fileSize = file.getSize();

        if (!fileType.equalsIgnoreCase("application/pdf")) {
            super.errorMessage("Only PDF files are allowed.");
            return;
        } else if (!(fileSize <= 1048576)) {

            super.errorMessage("File size is beyond the limit");
            return;
        }

        Path destinationPath = Paths.get(UPLOADS_DIRECTORY, fileName);

        try {
            byte[] content = file.getContents();

            Files.write(destinationPath, content);

            Document document = new Document();
            document.setDocumentName(fileName);
            document.setContentType(fileType);
            document.setDocumentSize(fileSize);
            document.setDocumentPath(destinationPath.toString());
            document.setClient(userBean.getCurrentClient().getName());

            documentRepository.save(document);
            super.infoMessage("File uploaded successfully!");
            loadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @PostConstruct
    public void init() {
        loadData();
    }

    public void delete(Document file) {
        documentRepository.delete(file.getId());
        super.infoMessage("Deleted Successfully");
        loadData();
    }

    public void loadData() {
        documentList = documentRepository.findAll();
    }

}
