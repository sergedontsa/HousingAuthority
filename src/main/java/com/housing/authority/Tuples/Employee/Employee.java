package com.housing.authority.Tuples.Employee;

import com.housing.authority.AuditModel.AuditModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee", schema = "vertical", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee extends AuditModel implements Serializable {

    private String employeeId;
    private String department;
    private String role;
    private String status;

    @Basic
    @Column(name = "id_address", insertable = false, updatable = false)
    private Integer idAddress;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private EmployeeAddress employeeAddress;


    @Id
    @Column(name = "employee_id", nullable = false, length = 50)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "department", nullable = true, length = 50)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 50)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
