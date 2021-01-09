package com.housing.authority.Tuples.Employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "employee_document", schema = "housingauthority", catalog = "")
@Setter
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeid")
    @JsonBackReference("employee_document")
    private Employee employee;


}
