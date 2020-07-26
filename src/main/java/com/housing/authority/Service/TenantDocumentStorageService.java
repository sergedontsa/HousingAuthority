package com.housing.authority.Service;

import com.housing.authority.Exception.DocumentStorageException;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Tenant.TenantDocumentRepository;
import com.housing.authority.Repository.Tenant.TenantRepository;
import com.housing.authority.Tuples.Tenant.TenantDocument;
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
public class TenantDocumentStorageService {
    private final Path location;
    @Autowired private final TenantDocumentRepository tenantDocumentRepository;
    @Autowired private final TenantRepository tenantRepository;
    @Autowired
    public TenantDocumentStorageService(TenantDocument tenantDocument, TenantDocumentRepository tenantDocumentRepository, TenantRepository tenantRepository){
        this.location = Paths.get(tenantDocument.getUploadDir()).toAbsolutePath().normalize();
        this.tenantRepository = tenantRepository;
        this.tenantDocumentRepository = tenantDocumentRepository;
        try {
            Files.createDirectories(this.location);
        }catch (Exception ex){
            throw new DocumentStorageException("Could not create a directory where the uploaded file will be store", ex);
        }
    }
    public String storeFile(MultipartFile file, String tenantId, String docType){
        //check if the tenant exist
        if (!this.tenantRepository.existsById(tenantId)){
            throw new ResourceNotFoundException("Tenant Id: " + tenantId + " could not be found");
        }
        //normalize the file name
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = "";
        try {
            if (originalFileName.contains("..")){
                throw new DocumentStorageException("Sorry! file name contains invalid path sequence " + originalFileName);
            }
            String fileExtension="";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }catch (Exception exception){
                exception.printStackTrace();
                fileExtension="";
            }
            fileName = tenantId+"_"+docType+fileExtension;
            //copy to the target location(replacing existing file with the same name)
            Path targetLocation = this.location.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            TenantDocument doc = this.tenantDocumentRepository.checkDocumentByTenantId(tenantId, docType);
            if (doc != null){
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                doc.setTenant(this.tenantRepository.getOne(tenantId));
                this.tenantDocumentRepository.save(doc);
            }else {
                TenantDocument newDoc = new TenantDocument();
                newDoc.setTenant(this.tenantRepository.getOne(tenantId));
                newDoc.setDocumentFormat(file.getContentType());
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                this.tenantDocumentRepository.save(newDoc);
            }
            return fileName;
        }catch (IOException ex){
            throw new DocumentStorageException("Could not store file " + fileName + " Please try again!", ex);
        }
    }
    public Resource loadFileAsResource(String fileName, String tenantId) throws Exception{
        if (!this.tenantRepository.existsById(tenantId)){
            throw new ResourceNotFoundException("Tenant Id: " + tenantId + " Could not be found");
        }
        try {
            Path filePath = this.location.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            }else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        }catch (MalformedURLException ex){
            throw new FileNotFoundException("File not found: " + fileName);
        }
    }
    public String getDocumentName(String tenantId, String docType){
        if (!this.tenantRepository.existsById(tenantId)){
            throw new ResourceNotFoundException("Tenant Id: " + tenantId + " could not be found");
        }
        return this.tenantDocumentRepository.getUploadDocumentPath(tenantId, docType);
    }
}
