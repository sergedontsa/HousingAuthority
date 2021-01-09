package com.housing.authority.Tuples.Employee;

import com.housing.authority.AuditModel.AuditModel;
import groovy.transform.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_identification", schema = "vertical", catalog = "")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeIdentification extends AuditModel implements Serializable {
    private Integer identificationId;
    private String employeeId;
    private String identificationType;
    private String issueDate;
    private String expiredDate;
    private String issueCountry;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identification_id", nullable = false)
    public Integer getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(Integer identificationId) {
        this.identificationId = identificationId;
    }

    @Basic
    @Column(name = "employee_id", nullable = true, length = 50)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "identification_type", nullable = false, length = 50)
    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    @Basic
    @Column(name = "issue_date", nullable = false, length = 50)
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Basic
    @Column(name = "expired_date", nullable = false, length = 50)
    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Basic
    @Column(name = "issue_country", nullable = false, length = 50)
    public String getIssueCountry() {
        return issueCountry;
    }

    public void setIssueCountry(String issueCountry) {
        this.issueCountry = issueCountry;
    }


}
