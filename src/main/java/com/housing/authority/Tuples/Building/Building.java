package com.housing.authority.Tuples.Building;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.housing.authority.AuditModel.AuditModel;
import com.housing.authority.Tuples.Apartment.Apartment;
import com.housing.authority.Tuples.Expensive.GeneralExpense;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "building")
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
    private Set<Apartment> apartments;

    @JsonBackReference("general_expense")
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GeneralExpense> expense;

    @JsonManagedReference("building_document")
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BuildingDocument> buildingDocument;

    ///-------------


    public List<GeneralExpense> getExpense() {
        return expense;
    }

    public void setExpense(List<GeneralExpense> expense) {
        this.expense = expense;
    }

    public List<BuildingDocument> getBuildingDocument() {
        return buildingDocument;
    }

    public void setBuildingDocument(List<BuildingDocument> buildingDocument) {
        this.buildingDocument = buildingDocument;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public int getNumLevel() {
        return numLevel;
    }

    public void setNumLevel(int numLevel) {
        this.numLevel = numLevel;
    }

    public int getNumBedRoom() {
        return numBedRoom;
    }

    public void setNumBedRoom(int numBedRoom) {
        this.numBedRoom = numBedRoom;
    }

    public int getNumBathRoom() {
        return numBathRoom;
    }

    public void setNumBathRoom(int numBathRoom) {
        this.numBathRoom = numBathRoom;
    }

    public int getNumLivingRoom() {
        return numLivingRoom;
    }

    public void setNumLivingRoom(int numLivingRoom) {
        this.numLivingRoom = numLivingRoom;
    }

    public int getNumKitchen() {
        return numKitchen;
    }

    public void setNumKitchen(int numKitchen) {
        this.numKitchen = numKitchen;
    }

    public int getNumDoor() {
        return numDoor;
    }

    public void setNumDoor(int numDoor) {
        this.numDoor = numDoor;
    }

    public int getNumWindows() {
        return numWindows;
    }

    public void setNumWindows(int numWindows) {
        this.numWindows = numWindows;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }

    public int getNumStudio() {
        return numStudio;
    }

    public void setNumStudio(int numStudio) {
        this.numStudio = numStudio;
    }

    public int getNumParkingSpace() {
        return numParkingSpace;
    }

    public void setNumParkingSpace(int numParkingSpace) {
        this.numParkingSpace = numParkingSpace;
    }

    public BuildingAddress getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(BuildingAddress buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }



    ///-------------



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

    public List<GeneralExpense> getExpens() {
        return expense;
    }

    public void setExpens(List<GeneralExpense> expens) {
        this.expense = expens;
    }
}
