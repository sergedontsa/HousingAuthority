package com.housing.authority.Controllers.Employee;

import com.housing.authority.Tuples.Employee.EmployeeDocumentResponse;
import com.housing.authority.Service.EmployeeDocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class EmployeeDocumentController {
    @Autowired
    private EmployeeDocumentStorageService employeeDocumentStorageService;
    @PostMapping("/uploadFile")
    public EmployeeDocumentResponse uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("employeeId") String employeeId, @RequestParam("docType") String docType){
        String fileName = employeeDocumentStorageService.storeFile(file, employeeId, docType);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new EmployeeDocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }
    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("employeeId") String employeeId, @RequestParam("docType") String docType, HttpServletRequest request){
        String filname = employeeDocumentStorageService.getDocumentName(employeeId, docType);
        Resource resource = null;
        if (filname != null && !filname.isEmpty()){
            try {
                resource = employeeDocumentStorageService.loadFileAsResources(filname, employeeId);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            //try to determine the file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }catch (IOException ex){
                ex.printStackTrace();
            }
            if (contentType == null){
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"").body(resource);

        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
