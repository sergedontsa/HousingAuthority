package com.housing.authority.Controllers.Apartment;

import com.housing.authority.Resources.Constant;
import com.housing.authority.Service.ApartmentDocumentStorageService;
import com.housing.authority.Tuples.Employee.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = Constant.APARTMENT_DOCUMENT_CONTROLLER)
public class ApartmentDocumentController {
    @Autowired
    private ApartmentDocumentStorageService apartmentDocumentStorageService;

    @CrossOrigin
    @PostMapping(value = Constant.APARTMENT_DOCUMENT_SAVE_WITH_APARTMENT_ID)
    public DocumentResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String apartmentId, @PathVariable String docType){
        String fileName = this.apartmentDocumentStorageService.storeFile(file, apartmentId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new DocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = Constant.APARTMENT_DOCUMENT_GET_WITH_APARTMENT_ID)
    public ResponseEntity<Resource> downloadFile(@PathVariable String apartmentId, @PathVariable String docType, HttpServletRequest request){
        String fileName = this.apartmentDocumentStorageService.getDocumentName(apartmentId, docType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()){
            try {
                resource = this.apartmentDocumentStorageService.loadFileAsResources(fileName, apartmentId);
            }catch (Exception exception){
                exception.printStackTrace();
            }
            String contentType  = null;
            try {
                assert resource != null;
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }catch (IOException ex){
                ex.printStackTrace();
            }
            if (contentType == null){
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\""+ resource.getFilename()+"\"").body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
