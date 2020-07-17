package com.housing.authority.Tuples.Employee;

import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Employee")
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Employee extends AuditModel implements Serializable {
    @Id
    @Column(name = "employeeid", nullable = false, length = 50)
    private String employeeId;

    @Basic
    @Column(name = "addressid", insertable = false, updatable = false)
    private String addressid;

    @Basic
    @Column(name = "detailid", insertable = false, updatable = false)
    private String detailid;

    @Basic
    @Column(name = "department", nullable = true, length = 50)
    private String department;

    @Basic
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;


    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "addressid")
    private EmployeeAddress employeeAddress;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "detailid")
    private EmployeeDetail employeeDetail;

    ///---------


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getDetailid() {
        return detailid;
    }

    public void setDetailid(String detailid) {
        this.detailid = detailid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public EmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }
}
