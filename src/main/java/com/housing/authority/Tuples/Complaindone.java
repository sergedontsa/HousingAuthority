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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "complaindone")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Complaindone extends AuditModel implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "confirmationid", nullable = false, length = 50)
    private String confirmationid;
    @Basic
    @Column(name = "timespend", nullable = false, length = 50)
    private String timespend;
    @Basic
    @Column(name = "employeeid")
    private String employeeid;

    @Basic
    @Column(name = "complainid", insertable = false, updatable = false)
    private String complainId;

    @Basic
    @Column(name = "cost", nullable = false, length = 50)
    private String cost;

    @OneToOne(targetEntity = Complain.class)
    @JoinColumn(name = "complainid")
    private Complain complain;




}
