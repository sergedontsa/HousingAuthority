package com.housing.authority.Tuples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "billing")
@Data
@EqualsAndHashCode
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Billing {


    @Id
    @Column(name = "billingid", nullable = false, length = 50)
    private String billingid;

//    @Basic
//    @Column(name = "apartmentid", insertable = false, updatable = false)
//    private String apartmentid;
//
//    @Basic
//    @Column(name = "tenantid", insertable = false, updatable = false)
//    private String tenantid;
//
//    @Basic
//    @Column(name = "buildingid", insertable = false, updatable = false)
//    private String buildingid;

    @Basic
    @Column(name = "start", nullable = false, length = 50)
    private String from;
    @Basic
    @Column(name = "end", nullable = false, length = 50)
    private String to;
    @Basic
    @Column(name = "period", nullable = false, length = 50)
    private String period;
    @Basic
    @Column(name = "registerdate", nullable = false, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = false, length = 50)
    private String lastupdate;
    @Basic
    @Column(name = "duedate", nullable = false, length = 50)
    private String duedate;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "tenantid")
//    private Tenant tenant;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "buildingid")
//    private Building building;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "apartmentid")
//    private Apartment apartment;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenantid")
    private Set<Tenant> tenants;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "buildingid")
    private Set<Building> building;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartmentid")
    private Set<Apartment> apartment;



}
