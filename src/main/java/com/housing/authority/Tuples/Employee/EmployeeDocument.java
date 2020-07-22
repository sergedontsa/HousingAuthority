package com.housing.authority.Tuples.Employee;

import com.housing.authority.AuditModel.AuditModel;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "employee_document", schema = "housingauthority", catalog = "")
public class EmployeeDocument extends AuditModel {
    @Id
    @Column(name = "documentid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    @Basic
    @Column(name = "employeeid", insertable = false, updatable = false)
    private String employeeId;
    @Basic
    @Column(name = "filename", nullable = false, length = 100)
    private String fileName;
    @Basic
    @Column(name = "documenttype", nullable = false, length = 500)
    private String documentType;
    @Basic
    @Column(name = "documentformat", nullable = false, length = 500)
    private String documentFormat;
    @Basic
    @Column(name = "uploaddir", nullable = false, length = 500)
    private String uploadDir;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeeid")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }


    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }


    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
