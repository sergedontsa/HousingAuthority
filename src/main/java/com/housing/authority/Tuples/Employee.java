package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

}
