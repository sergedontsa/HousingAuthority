package com.housing.authority.Tuples;

import lombok.*;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Complaindone implements Serializable {
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
    @Column(name = "complainid")
    private String complainId;

    @Basic
    @Column(name = "cost", nullable = false, length = 50)
    private String cost;
    @Basic
    @Column(name = "registerdate", nullable = false, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = false, length = 50)
    private String lastupdate;



}
