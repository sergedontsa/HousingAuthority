package com.housing.authority.Tuples.Apartment;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "apartment_dimension", schema = "housingauthority", catalog = "")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApartmentDimension extends AuditModel implements Serializable {
    @Id
    @Basic
    @Column(name = "apartmentid", nullable = false)
    private String apartmentId;
    @Basic
    @Column(name = "number_room", nullable = false)
    private int numberRoom;
    @Basic
    @Column(name = "bedroom_1_size", nullable = true, length = 50)
    private String bedroom1Size;
    @Basic
    @Column(name = "bedroom_2_size", nullable = true, length = 50)
    private String bedroom2Size;
    @Basic
    @Column(name = "bedroom_3_size", nullable = true, length = 50)
    private String bedroom3Size;
    @Basic
    @Column(name = "kitchen_size", nullable = true, length = 50)
    private String kitchenSize;
    @Basic
    @Column(name = "bathroom_1_size", nullable = true, length = 50)
    private String bathroom1Size;
    @Basic
    @Column(name = "bathromm_2_size", nullable = true, length = 50)
    private String bathromm2Size;
    @Basic
    @Column(name = "livingroom_1_size", nullable = true, length = 50)
    private String livingroom1Size;
    @Basic
    @Column(name = "livingroom_2_size", nullable = true, length = 50)
    private String livingroom2Size;
    @Basic
    @Column(name = "terrace_1_size", nullable = true, length = 50)
    private String terrace1Size;
    @Basic
    @Column(name = "terrace_2_size", nullable = true, length = 50)
    private String terrace2Size;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "extra", nullable = true, length = 500)
    private String extra;

    ///----------------------------------------------------

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getBedroom1Size() {
        return bedroom1Size;
    }

    public void setBedroom1Size(String bedroom1Size) {
        this.bedroom1Size = bedroom1Size;
    }

    public String getBedroom2Size() {
        return bedroom2Size;
    }

    public void setBedroom2Size(String bedroom2Size) {
        this.bedroom2Size = bedroom2Size;
    }

    public String getBedroom3Size() {
        return bedroom3Size;
    }

    public void setBedroom3Size(String bedroom3Size) {
        this.bedroom3Size = bedroom3Size;
    }

    public String getKitchenSize() {
        return kitchenSize;
    }

    public void setKitchenSize(String kitchenSize) {
        this.kitchenSize = kitchenSize;
    }

    public String getBathroom1Size() {
        return bathroom1Size;
    }

    public void setBathroom1Size(String bathroom1Size) {
        this.bathroom1Size = bathroom1Size;
    }

    public String getBathromm2Size() {
        return bathromm2Size;
    }

    public void setBathromm2Size(String bathromm2Size) {
        this.bathromm2Size = bathromm2Size;
    }

    public String getLivingroom1Size() {
        return livingroom1Size;
    }

    public void setLivingroom1Size(String livingroom1Size) {
        this.livingroom1Size = livingroom1Size;
    }

    public String getLivingroom2Size() {
        return livingroom2Size;
    }

    public void setLivingroom2Size(String livingroom2Size) {
        this.livingroom2Size = livingroom2Size;
    }

    public String getTerrace1Size() {
        return terrace1Size;
    }

    public void setTerrace1Size(String terrace1Size) {
        this.terrace1Size = terrace1Size;
    }

    public String getTerrace2Size() {
        return terrace2Size;
    }

    public void setTerrace2Size(String terrace2Size) {
        this.terrace2Size = terrace2Size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
