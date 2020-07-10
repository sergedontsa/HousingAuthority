package com.housing.authority.Tuples;

import com.housing.authority.AuditModel.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "building_address", schema = "housingauthority", catalog = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingAddress extends AuditModel implements Serializable {

    @Id
    @Column(name = "addressid", nullable = false)
    private int id;
    @Basic
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingid;
    @Basic
    @Column(name = "number", nullable = true)
    private Integer number;
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Basic
    @Column(name = "province", nullable = false, length = 50)
    private String province;
    @Basic
    @Column(name = "zipcode", nullable = false, length = 50)
    private String zipcode;
    @Basic
    @Column(name = "country", nullable = false, length = 50)
    private String country;

}
