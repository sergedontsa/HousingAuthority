package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "apartment")
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Apartment extends AuditModel implements Serializable {

    @Id
    @Column(name = "apartmentid", nullable = false, length = 50)
    private String apartmentID;

    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "buildingid", nullable = false)
    @JsonBackReference("apartment")
    private Building building;



}
