package com.housing.authority.Tuples;

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



}
