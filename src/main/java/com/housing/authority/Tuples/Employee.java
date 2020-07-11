package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid", nullable = false, length = 50)
    private String employeeId;

    @Basic
    @Column(name = "addressid", insertable = false, updatable = false)
    private String addressid;

    @Basic
    @Column(name = "department", nullable = true, length = 50)
    private String department;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressid")
    private EmployeeAddress employeeAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonIgnore
    private EmployeeDetail employeeDetail;

}
