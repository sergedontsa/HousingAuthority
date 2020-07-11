package com.housing.authority.Tuples;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listening")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Listening {
    @Id
    @Basic
    @Column(name = "apartmentid", nullable = false, length = 255)
    private String apartmentId;
    @Basic
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingid;
    @Basic
    @Column(name = "numbathroom", nullable = false)
    private int numBathRoom;
    @Basic
    @Column(name = "numbedroom", nullable = false)
    private int numBedRoom;
    @Basic
    @Column(name = "numcloset", nullable = false)
    private int numCloset;
    @Basic
    @Column(name = "numkitchen", nullable = false)
    private int numKitchen;
    @Basic
    @Column(name = "numlivingroom", nullable = false)
    private int numLivingRoom;
    @Basic
    @Column(name = "numwindows", nullable = true)
    private Integer numWindows;
    @Basic
    @Column(name = "iswithbath", nullable = false)
    private byte isWithBath;
    @Basic
    @Column(name = "iswithwaterboiler", nullable = false)
    private byte isWithWaterBoiler;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Basic
    @Column(name = "registerdate", nullable = true, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = true, length = 50)
    private String lastupdate;

}
