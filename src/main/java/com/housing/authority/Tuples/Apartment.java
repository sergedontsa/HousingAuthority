package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(name = "numbedroom")
    private int numBedRoom;
    @NotNull
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @NotNull
    @Column(name = "numbathroom")
    private int numBathRoom;
    @NotNull
    @Column(name = "numkitchen")
    private int numKitchen;
    @NotNull
    @Column(name = "numcloset")
    private int numCloset;
    @NotNull
    @Column(name = "numwindows")
    private int numWindows;
    @NotNull
    @Column(name = "iswithbath", nullable = false)
    private boolean isWithBath;
    @NotNull
    @Column(name = "iswithwaterboiler", nullable = false)
    @NotNull
    private boolean isWithWaterBoiler;
    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "buildingid", nullable = false)
    @JsonBackReference("apartment")
    private Building building;




}
