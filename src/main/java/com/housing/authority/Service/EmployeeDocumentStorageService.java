package com.housing.authority.Service;

import com.housing.authority.Exception.DocumentStorageException;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Employee.EmployeeDocumentRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Tuples.Employee.EmployeeDocument;
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
public class EmployeeDocumentStorageService
{
    private final Path location;

    @Autowired private final EmployeeDocumentRepository employeeDocumentRepository;
    @Autowired private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeDocumentStorageService(EmployeeDocument employeeDocument,
                                          EmployeeRepository employeeRepository,
                                          EmployeeDocumentRepository employeeDocumentRepository){
        this.location = Paths.get(employeeDocument.getUploadDir()).toAbsolutePath().normalize();
        this.employeeRepository = employeeRepository;
        this.employeeDocumentRepository = employeeDocumentRepository;

        try {
            Files.createDirectories(this.location);
        }catch (Exception ex){
            throw new DocumentStorageException("Could not create directory where the uploaded file will be store" , ex);
        }
    }
    public String storeFile(MultipartFile file, String employeeId, String docType){
        //check if employee exist
        if (!this.employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Employee Id " + employeeId + " could not be found");
        }
        //normalize file name
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = "";
        try {
            if (originalFileName.contains("..")){
                throw new DocumentStorageException("Sorry! file name contains invalid path sequence "+ originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }catch (Exception exception){
                fileExtension="";
            }
            fileName = employeeId+"_"+docType+fileExtension;
            //copy file to the target location(replacing existing file with the same name
            Path targetLocation = this.location.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            EmployeeDocument doc = employeeDocumentRepository.checkDocumentByEmployeeId(employeeId, docType);
            if (doc != null){
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                doc.setEmployee(this.employeeRepository.getOne(employeeId));
                employeeDocumentRepository.save(doc);
            }else {
                EmployeeDocument newDoc = new EmployeeDocument();
                newDoc.setEmployeeId(employeeId);
                newDoc.setDocumentFormat(file.getContentType());
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                newDoc.setEmployee(this.employeeRepository.getOne(employeeId));
                employeeDocumentRepository.save(newDoc);
            }
            return fileName;

        }catch (IOException ex){
            throw new DocumentStorageException("Could not store file " + fileName + " Please try again!", ex);
        }
    }
    public Resource loadFileAsResources(String fileName, String employeeId) throws Exception{
        //check if employee exist
        if (!this.employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Employee Id " + employeeId + " could not be found");
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
            throw new FileNotFoundException("File not found " + fileName);
        }
    }
    public String getDocumentName(String employeeId, String docType){
        if (!this.employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Employee Id " + employeeId + " could not be found");
        }
        return employeeDocumentRepository.getUploadDocumentPath(employeeId, docType);
    }

}
