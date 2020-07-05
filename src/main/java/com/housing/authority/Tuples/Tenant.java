package com.housing.authority.Tuples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Tenant{

    @Id
    @Column(name = "tenantid", nullable = false, length = 50)
    private String tenantid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartmentid", referencedColumnName = "apartmentid")
    private Apartment apartment;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buildingid", referencedColumnName = "buildingid")
    private Building building;

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
    @Basic
    @Column(name = "registerdate", nullable = true, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = true, length = 50)
    private String lastupdate;

}
