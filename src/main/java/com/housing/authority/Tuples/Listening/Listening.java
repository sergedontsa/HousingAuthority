package com.housing.authority.Tuples.Listening;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listening")
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

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public int getNumBathRoom() {
        return numBathRoom;
    }

    public void setNumBathRoom(int numBathRoom) {
        this.numBathRoom = numBathRoom;
    }

    public int getNumBedRoom() {
        return numBedRoom;
    }

    public void setNumBedRoom(int numBedRoom) {
        this.numBedRoom = numBedRoom;
    }

    public int getNumCloset() {
        return numCloset;
    }

    public void setNumCloset(int numCloset) {
        this.numCloset = numCloset;
    }

    public int getNumKitchen() {
        return numKitchen;
    }

    public void setNumKitchen(int numKitchen) {
        this.numKitchen = numKitchen;
    }

    public int getNumLivingRoom() {
        return numLivingRoom;
    }

    public void setNumLivingRoom(int numLivingRoom) {
        this.numLivingRoom = numLivingRoom;
    }

    public Integer getNumWindows() {
        return numWindows;
    }

    public void setNumWindows(Integer numWindows) {
        this.numWindows = numWindows;
    }

    public byte getIsWithBath() {
        return isWithBath;
    }

    public void setIsWithBath(byte isWithBath) {
        this.isWithBath = isWithBath;
    }

    public byte getIsWithWaterBoiler() {
        return isWithWaterBoiler;
    }

    public void setIsWithWaterBoiler(byte isWithWaterBoiler) {
        this.isWithWaterBoiler = isWithWaterBoiler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
