package com.housing.authority.Controllers.Tenant;

import com.housing.authority.Resources.Constant;
import com.housing.authority.Service.TenantDocumentStorageService;
import com.housing.authority.Tuples.Employee.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = Constant.TENANT_DOCUMENT_CONTROLLER)
public class TenantDocumentController {

    @Autowired
    private TenantDocumentStorageService tenantDocumentStorageService;
    @CrossOrigin
    @PostMapping(value = Constant.TENANT_DOCUMENT_SAVE_WITH_TENANT_ID)
    public DocumentResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String tenantId, @PathVariable String docType){
        String fileName = this.tenantDocumentStorageService.storeFile(file, tenantId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new DocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = Constant.TENANT_DOCUMENT_GET_WITH_TENANT_ID)
    public ResponseEntity<Resource> downloadFile(@PathVariable String tenantId, @PathVariable String docType, HttpServletRequest request){
        String fileName = this.tenantDocumentStorageService.getDocumentName(tenantId, docType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()){
            try {
                resource = this.tenantDocumentStorageService.loadFileAsResource(fileName, tenantId);
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
