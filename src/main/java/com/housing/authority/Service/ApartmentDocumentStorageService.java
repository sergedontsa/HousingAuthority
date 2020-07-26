package com.housing.authority.Service;

import com.housing.authority.Exception.DocumentStorageException;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Apartment.ApartmentDocumentRepository;
import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Tuples.Apartment.ApartmentDocument;
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
public class ApartmentDocumentStorageService
{
    private final Path location;
    @Autowired
    private final ApartmentDocumentRepository apartmentDocumentRepository;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentDocumentStorageService(ApartmentDocument apartmentDocument,  ApartmentRepository apartmentRepository,
            ApartmentDocumentRepository apartmentDocumentRepository){

        this.location = Paths.get(apartmentDocument.getUploadDir()).toAbsolutePath().normalize();
        this.apartmentRepository = apartmentRepository;
        this.apartmentDocumentRepository = apartmentDocumentRepository;

        try {
            Files.createDirectories(this.location);
        }catch (Exception ex){
            throw new DocumentStorageException("Could not create directory where the uploaded file will be store" , ex);
        }
    }

    public String storeFile(MultipartFile file, String apartmentId, String docType){
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Apartment Id: " + apartmentId + " could not be found");
        }
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = "";
        try {
            if (originalFileName.contains("..")){
                throw new DocumentStorageException("Sorry! file name contains an invalid path sequence " + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }catch (Exception ex){
                ex.printStackTrace();
                fileExtension="";
            }
            fileName = apartmentId+"_"+docType+fileExtension;
            Path targetLocation = this.location.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            ApartmentDocument doc = this.apartmentDocumentRepository.checkDocumentByApartmentId(apartmentId, docType);
            if (doc != null){
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                doc.setApartment(this.apartmentRepository.getOne(apartmentId));
                doc.setApartmentId(apartmentId);
                this.apartmentDocumentRepository.save(doc);
            }else {
                ApartmentDocument newDoc = new ApartmentDocument();
                newDoc.setApartmentId(apartmentId);
                newDoc.setApartment(this.apartmentRepository.getOne(apartmentId));
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                newDoc.setDocumentFormat(file.getContentType());
                this.apartmentDocumentRepository.save(newDoc);
            }
            return fileName;
        }catch (IOException ex){
            throw new DocumentStorageException("Could not store file " + fileName + " please try again " ,ex);
        }
    }
    public Resource loadFileAsResources(String fileName, String apartmentId) throws Exception{
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Apartment Id: " + apartmentId + " could not be found");
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
    public String getDocumentName(String apartmentId, String docType){
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Apartment Id: " + apartmentId + " could not be found");
        }
        return apartmentDocumentRepository.getUploadedDocument(apartmentId, docType);
    }


}
