package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "apartment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Apartment extends AuditModel implements Serializable {

    @Id
    @Column(name = "apartmentid", nullable = false, length = 50)
    @Basic
    private String apartmentID;

    @Basic
    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;

    @Basic
    @Column(name = "dimensionid", insertable = false, updatable = false)
    private String dimensionid;
    @Basic
    @Column(name = "feeid", insertable = false, updatable = false)
    private String feeid;

    @Basic
    @Column(name = "apartment_number", length = 50, nullable = true)
    private String apartment_number;

    @Basic
    @Column(name = "numbedroom")
    private int numBedRoom;
    @Basic
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @Basic
    @Column(name = "numbathroom")
    private int numBathRoom;
    @Basic
    @Column(name = "numkitchen")
    private int numKitchen;
    @Basic
    @Column(name = "numcloset")
    private int numCloset;
    @Basic
    @Column(name = "numwindows")
    private int numWindows;
    @Basic
    @Column(name = "iswithbath", nullable = false)
    private boolean isWithBath;
    @Basic
    @Column(name = "iswithwaterboiler", nullable = false)
    private boolean isWithWaterBoiler;
    @Basic
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingid", nullable = false)
    @JsonBackReference("apartment")
    private Building building;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dimensionid")
    private ApartmentDimension apartmentDimension;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feeid")
    private ApartmentFee apartmentFee;

}
