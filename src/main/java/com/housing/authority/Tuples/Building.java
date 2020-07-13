package com.housing.authority.Tuples;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Building extends AuditModel {

    @Id
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingId;

    @Basic
    @Column(name = "addressid", insertable = false, updatable = false)
    private String addressid;

    @Basic
    @Column(name = "numlevel")
    private int numLevel;
    @Basic
    @Column(name = "numbedroom")
    private int numBedRoom;
    @Basic
    @Column(name = "numbathroom")
    private int numBathRoom;
    @Basic
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @Basic
    @Column(name = "numkitchen")
    private int numKitchen;
    @Basic
    @Column(name = "numdoor")
    private int numDoor;
    @Basic
    @Column(name = "numwindows")
    private int numWindows;
    @Basic
    @Column(name = "numapartment")
    private int numApartment;
    @Basic
    @Column(name = "numstudio")
    private int numStudio;
    @Basic
    @Column(name = "numparkingspace")
    private int numParkingSpace;
    @Basic
    @Column(name = "iswithpool")
    private boolean isWithPool;
    @Basic
    @Column(name = "iswithelevator")
    private boolean isWithElevator;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "addressid")
     private BuildingAddress buildingAddress;


    @JsonManagedReference("apartment")
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Column(nullable = true)
    private List<Apartment> apartments;



    public boolean isWithPool() {
        return isWithPool;
    }

    public void setWithPool(boolean withPool) {
        isWithPool = withPool;
    }

    public boolean isWithElevator() {
        return isWithElevator;
    }

    public void setWithElevator(boolean withElevator) {
        isWithElevator = withElevator;
    }


}
