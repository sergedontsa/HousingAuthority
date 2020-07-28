package com.housing.authority.Tuples.Tenant;

import com.housing.authority.AuditModel.AuditModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tenant_sub", schema = "housingauthority", catalog = "")
public class TenantSub extends AuditModel {
    @Id
    @Column(name = "tenantid", nullable = false, length = 50)
    private String tenantid;
    @Basic
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingid;
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
    @Column(name = "price", nullable = false, length = 20)
    private String price;
    @Basic
    @Column(name = "profession", nullable = false, length = 50)
    private String profession;
    @Basic
    @Column(name = "deposite", nullable = true, length = 50)
    private String deposite;
    @Basic
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @OneToOne
    @JoinColumn(name = "parentid")
    private Tenant tenant;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenantid() {
        return tenantid;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPrice() {
        return price;
    }

    public String getProfession() {
        return profession;
    }

    public String getDeposite() {
        return deposite;
    }

    public String getStatus() {
        return status;
    }
}
