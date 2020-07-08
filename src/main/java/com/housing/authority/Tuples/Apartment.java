package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "apartment")
@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Apartment implements Serializable {

    @Id
    @Column(name = "apartmentid", nullable = false, length = 50)
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
    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;
    @Column(name = "lastupdate")
    private String lastupdate;

//    @OneToOne(mappedBy = "apartment")
//    private Billing billing;

//    @ManyToOne
//    private Billing billing;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Building building;



}
