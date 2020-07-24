package com.housing.authority.Controllers.Employee;

import com.housing.authority.Resources.Constant;
import com.housing.authority.Tuples.Employee.DocumentResponse;
import com.housing.authority.Service.EmployeeDocumentStorageService;
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
@RequestMapping(value = Constant.EMPLOYEE_DOCUMENT_CONTROLLER)
public class EmployeeDocumentController {
    @Autowired
    private EmployeeDocumentStorageService employeeDocumentStorageService;

    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_DOCUMENT_SAVE_WITH_EMPLOYEE_ID)
    public DocumentResponse uploadFile(@RequestParam("file")MultipartFile file, @PathVariable String employeeId, @PathVariable String docType){
        String fileName = employeeDocumentStorageService.storeFile(file, employeeId, docType);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new DocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = Constant.EMPLOYEE_DOCUMENT_GET_WITH_EMPLOYEE_ID)
    public ResponseEntity<Resource> downloadFile(@PathVariable String employeeId, @PathVariable String docType, HttpServletRequest request){
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
