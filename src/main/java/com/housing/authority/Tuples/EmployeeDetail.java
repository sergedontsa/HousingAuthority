package com.housing.authority.Tuples;


import com.housing.authority.AuditModel.AuditModel;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * By Serge Dontsa
 */

@Entity
@Table(name = "employee_detail", schema = "housingauthority", catalog = "",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phonenumber"),
        @UniqueConstraint(columnNames = "idnumber")
})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDetail extends AuditModel implements Serializable {

    @Id
    @Basic
    @Column(name = "employeeid", nullable = false, length = 50)
    private String employeeid;

    @Basic
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Basic
    @Column(name = "middlename", nullable = true, length = 50)
    private String middlename;
    @Basic
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "phonenumber", nullable = false, length = 50)
    private String phonenumber;
    @Basic
    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @Basic
    @Column(name = "idtype", nullable = false, length = 50)
    private String idtype;
    @Basic
    @Column(name = "idnumber", nullable = false, length = 50)
    private String idnumber;
    @Basic
    @Column(name = "issuedate", nullable = false, length = 50)
    private String issuedate;
    @Basic
    @Column(name = "expiredate", nullable = false, length = 50)
    private String expiredate;
    @Basic
    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

//    @OneToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "employeeid", nullable = false)
//    private Employee employee;


}
