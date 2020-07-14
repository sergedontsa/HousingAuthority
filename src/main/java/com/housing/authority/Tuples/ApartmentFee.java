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
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "apartment_fee", schema = "housingauthority", catalog = "")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApartmentFee extends AuditModel implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "apartmentid", nullable = false)
    private String apartmentId;
    @Basic
    @Column(name = "fee", nullable = false, length = 50)
    private String fee;
    @Basic
    @Column(name = "currency", nullable = false, length = 50)
    private String currency;
    @Basic
    @Column(name = "registerdate", nullable = false)
    private Timestamp registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = false)
    private Timestamp lastupdate;

}
