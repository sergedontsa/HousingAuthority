package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.housing.authority.AuditModel.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tenant")
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
public class Tenant extends AuditModel {

    @Id
    @Column(name = "tenantid", nullable = false, length = 50)
    private String tenantid;

    @Basic
    @Column(name ="apartmentid", insertable = false, updatable = false)
    //@Column(name ="apartmentid", nullable = false, length = 50)
    private String apartmentid;
    @Basic
    @Column(name ="buildingid", nullable = false, length = 50)
    private String buildingid;

    @Basic
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Basic
    @Column(name = "middlename", nullable = true, length = 50)
    private String middlename;
    @Basic
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "phonenumber", nullable = false, length = 50)
    private String phonenumber;
    @Basic
    @Column(name = "price")
    private String price;
    @Basic
    @Column(name = "profession", nullable = false, length = 50)
    private String profession;
    @Basic
    @Column(name = "deposite", nullable = false, length = 50)
    private String deposite;
    @Basic
    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "apartmentid")
    private Apartment apartment;




//    @ManyToOne
//    private Billing billing;







}
