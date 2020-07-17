package com.housing.authority.Tuples.Employee;


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

    //------------------------------------


    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
