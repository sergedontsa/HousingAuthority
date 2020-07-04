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

@Entity(name = "billing")
@Data
@EqualsAndHashCode
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Billing {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenantid", referencedColumnName = "tenantid")
    private Tenant tenant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buildingid", referencedColumnName = "buildingid")
    private Building building;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartmentid", referencedColumnName = "apartmentid")
    private Apartment apartment;

    @Id
    @Column(name = "billingid", nullable = false, length = 50)
    private String billingid;
    @Basic
    @Column(name = "from", nullable = false, length = 50)
    private String from;
    @Basic
    @Column(name = "to", nullable = false, length = 50)
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


}
