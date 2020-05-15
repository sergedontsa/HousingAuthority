package com.housing.authority.Tuples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employees {
    @Id
    @Column(name = "employeeid", nullable = false, length = 50)
    private String employeeId;
    @Basic
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "middlename", nullable = true, length = 50)
    private String middleName;
    @Basic
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "department", nullable = true, length = 50)
    private String department;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Basic
    @Column(name = "registerdate", nullable = false, length = 50)
    private String registerDate;
    @Basic
    @Column(name = "lastupdate", nullable = true, length = 50)
    private String lastupdate;

}
