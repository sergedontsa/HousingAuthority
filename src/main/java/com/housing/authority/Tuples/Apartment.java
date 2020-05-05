package com.housing.authority.Tuples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "Apartment")
@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Apartment implements Serializable {

    @Id
    @Column(name = "apartmentid", nullable = false, unique = true)
    private String apartmentID;
    @Column(name = "numbedroom")
    private int numBedRoom;
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @Column(name = "numbathroom")
    private int numBathRoom;
    @Column(name = "numkitchen")
    private int numKitchen;
    @Column(name = "numcloset")
    private int numCloset;
    @Column(name = "numwindows")
    private int numWindows;
    @Column(name = "iswithbath", nullable = false)
    private boolean isWithBath;
    @Column(name = "iswithwaterboiler", nullable = false)
    private boolean isWithWaterBoiler;
    @Column(name = "status")
    private String status;
    @Column(name = "registerdate")
    private String registerdate;
    @Column(name = "buildingid")
    private String buildingid;
    @Column(name = "lastupdate")
    private String lastupdate;



}
