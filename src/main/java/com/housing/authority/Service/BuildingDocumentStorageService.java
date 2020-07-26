package com.housing.authority.Service;

import com.housing.authority.Exception.DocumentStorageException;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Building.BuildingDocumentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Tuples.Building.BuildingDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class BuildingDocumentStorageService {

    private final Path location;
    @Autowired private final BuildingDocumentRepository buildingDocumentRepository;
    @Autowired private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingDocumentStorageService(BuildingDocument buildingDocument, BuildingDocumentRepository buildingDocumentRepository, BuildingRepository buildingRepository){
        this.location = Paths.get(buildingDocument.getUploadDir()).toAbsolutePath().normalize();
        this.buildingDocumentRepository = buildingDocumentRepository;
        this.buildingRepository = buildingRepository;
        try {
            Files.createDirectories(this.location);
        }catch (Exception ex){
            throw new DocumentStorageException("Could not create directory where the uploaded file will be stored", ex);
        }
    }
    public String storeFile(MultipartFile file, String buildingId, String docType){
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("Building Id: " + buildingId + " could not be found");
        }
        //Normalize file name
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = "";
        try {
            if (originalFileName.contains(".."))
                throw new DocumentStorageException("Sorry! file name invalid " + originalFileName);
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }catch (Exception ex){
                fileExtension="";
            }
            fileName = buildingId+"_"+docType+fileExtension;
            //copy file to the target location(replacing existing file with the same name
            Path targetLocation = this.location.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            BuildingDocument doc = buildingDocumentRepository.checkDocumentByBuildingId(buildingId, docType);
            if (doc != null){
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                doc.setBuilding(this.buildingRepository.getOne(buildingId));
                this.buildingDocumentRepository.save(doc);
            }else {
                BuildingDocument newDoc = new BuildingDocument();
                newDoc.setBuilding(this.buildingRepository.getOne(buildingId));
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                newDoc.setDocumentFormat(file.getContentType());
                this.buildingDocumentRepository.save(newDoc);
            }
            return fileName;
        }catch (IOException ex){
            throw new DocumentStorageException("Could not store file " + fileName + " please try again ", ex);
        }
    }

    public Resource loadFileResources(String fileName, String buildingId) throws Exception{
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("Building Id " + buildingId + " could not be found");
        }
        try {
            Path filePath = this.location.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            }else {
                throw new FileNotFoundException("File not found: " + fileName);
            }
        }catch (MalformedURLException ex){
            throw new FileNotFoundException("File not found" + fileName);
        }
    }
    public String getDocumentName(String buildingId, String docType){
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("Building Id " + buildingId + " could not be found");
        }
        return buildingDocumentRepository.getUploadedDocument(buildingId, docType);
    }
}
