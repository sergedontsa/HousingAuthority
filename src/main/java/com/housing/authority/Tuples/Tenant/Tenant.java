package com.housing.authority.Tuples.Tenant;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.housing.authority.AuditModel.AuditModel;
import com.housing.authority.Tuples.Apartment.Apartment;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tenant", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phonenumber")
})
public class Tenant extends AuditModel {

    @Id
    @Column(name = "tenantid", nullable = false, length = 50)
    private String tenantid;

    @Basic
    @Column(name ="apartmentid", insertable = false, updatable = false)
    private String apartmentid;

    @Basic
    @Column(name ="buildingid", nullable = false, length = 50)
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
    @Column(name = "price")
    private String price;
    @Basic
    @Column(name = "profession", nullable = false, length = 50)
    private String profession;
    @Basic
    @Column(name = "deposite", nullable = false, length = 50)
    private String deposite;
    @Basic
    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "apartmentid")
    private Apartment apartment;

    @JsonManagedReference("tenant_expense")
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TenantExpense> tenantExpenses;

    //-------------------



    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getApartmentid() {
        return apartmentid;
    }

    public void setApartmentid(String apartmentid) {
        this.apartmentid = apartmentid;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }


    public List<TenantExpense> getTenantExpenses() {
        return tenantExpenses;
    }

    public void setTenantExpenses(List<TenantExpense> tenantExpenses) {
        this.tenantExpenses = tenantExpenses;
    }
}
