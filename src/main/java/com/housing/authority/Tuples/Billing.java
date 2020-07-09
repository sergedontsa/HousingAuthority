package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.housing.authority.AuditModel.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;



@Entity
@Table(name = "billing")
@Data
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Billing extends AuditModel implements Serializable {


    @Id
    @Column(name = "billingid", nullable = false, length = 50)
    private String billingid;

    @Basic
    @Column(name = "tenantid", insertable = false, updatable = false)
    private String tenantid;
    @Basic
    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;
    @Basic
    @Column(name = "apartmentid", insertable = false, updatable = false)
    private String apartmentid;


    @Basic
    @Column(name = "start", nullable = false, length = 50)
    private String start;

    @Basic
    @Column(name = "end", nullable = false, length = 50)
    private String end;
    @Basic
    @Column(name = "period", nullable = false, length = 50)
    private String period;
    @Basic
    @Column(name = "duedate", nullable = false, length = 50)
    private String duedate;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tenantid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityReference(alwaysAsId = true)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Building building;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "apartmentid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityReference(alwaysAsId = true)
    private Apartment apartment;




}
