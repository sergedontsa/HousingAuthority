package com.housing.authority.Service;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Exception.EmployeeDocumentStorageException;
import com.housing.authority.Repository.EmployeeDocumentRepository;
import com.housing.authority.Repository.EmployeeRepository;
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
    private final Path fileStorageLocation;

    @Autowired private final EmployeeDocumentRepository docStorageRepo;
    @Autowired private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDocumentStorageService(EmployeeDocument fileStorageProperties, EmployeeRepository employeeRepository, EmployeeDocumentRepository employeeDocumentRepository){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();
        this.employeeRepository = employeeRepository;
        this.docStorageRepo = employeeDocumentRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new EmployeeDocumentStorageException("Could not create directory where the uploaded file will be store" , ex);
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
                throw new EmployeeDocumentStorageException("Sorry! file name contains invalid path sequence "+ originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }catch (Exception exception){
                fileExtension="";
            }
            fileName = employeeId+"_"+docType+fileExtension;
            //copy file to the target location(replacing existing file with the same name
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            EmployeeDocument doc = docStorageRepo.checkDocumentByEmployeeId(employeeId, docType);
            if (doc != null){
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                doc.setEmployee(this.employeeRepository.getOne(employeeId));
                docStorageRepo.save(doc);
            }else {
                EmployeeDocument newDoc = new EmployeeDocument();
                newDoc.setEmployeeId(employeeId);
                newDoc.setDocumentFormat(file.getContentType());
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                newDoc.setEmployee(this.employeeRepository.getOne(employeeId));
                docStorageRepo.save(newDoc);
            }
            return fileName;

        }catch (IOException ex){
            throw new EmployeeDocumentStorageException("Could not store file " + fileName + " Please try again!", ex);
        }
    }
    public Resource loadFileAsResources(String fileName, String employeeId) throws Exception{
        //check if employee exist
        if (!this.employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Employee Id " + employeeId + " could not be found");
        }
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
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
        return docStorageRepo.getUploadDocumentPath(employeeId, docType);
    }

}
