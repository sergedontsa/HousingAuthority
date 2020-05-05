package com.housing.authority.Tuples;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Tenant {

    @Id
    @Column(name = "tenantid", nullable = false, length = 50)
    private String tenantid;
    @Basic
    @Column(name = "apartmentid", nullable = false, length = 255)
    private String apartmentid;

    @Basic
    @Column(name = "buildingid",nullable = false, length = 50)
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
    @Column(name = "profession", nullable = false, length = 50)
    private String profession;
    @Basic
    @Column(name = "deposite", nullable = false, length = 50)
    private String deposite;
    @Basic
    @Column(name = "registerdate", nullable = true, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = true, length = 50)
    private String lastupdate;

}
