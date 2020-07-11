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

@Entity
@Table(name = "schedule")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends AuditModel {
    @Id
    @Column(name = "scheduleid", nullable = false)
    private int scheduleid;

    @Basic
    @Column(name = "date", nullable = false, length = 50)
    private String date;
    @Basic
    @Column(name = "from", nullable = false, length = 50)
    private String from;
    @Basic
    @Column(name = "to", nullable = false, length = 50)
    private String to;

    @Basic
    @Column(name = "hours", nullable = false, length = 50)
    private String hours;

    @Basic
    @Column(name = "description", nullable = false, length = 500)
    private String description;





}
