package com.housing.authority.Tuples.Employee;

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

    //------------------------------


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
