package com.housing.authority.Tuples;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@DynamicUpdate
public class Complain implements Serializable {
    @Id
    @Column(name = "complainid", nullable = false, length = 50)
    private String complainid;
    @Basic
    @Column(name = "personid", nullable = false, length = 50)
    private String personid;
    @Basic
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic
    @Column(name = "body", nullable = false, length = 500)
    private String body;
    @Basic
    @Column(name = "phonenumber", nullable = false, length = 20)
    private String phonenumber;
    @Basic
    @Column(name = "severity", nullable = false, length = 20)
    private String severity;
    @Basic
    @Column(name = "address", nullable = false, length = 200)
    private String address;
    @Basic
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    @Basic
    @Column(name = "assignto", nullable = false, length = 50)
    private String assignto;
    @Basic
    @Column(name = "registerdate", nullable = false, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = false, length = 50)
    private String lastupdate;

}
