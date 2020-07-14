package com.housing.authority.Tuples;

import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee_address", schema = "housingauthority", catalog = "")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class EmployeeAddress extends AuditModel implements Serializable {
    @Id
    @Basic
    @Column(name = "employeeid", nullable = false, length = 50)
    private String employeeId;
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
