package com.housing.authority.Tuples;

import com.housing.authority.AuditModel.AuditModel;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "complain")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@DynamicUpdate
@Data
public class Complain extends AuditModel implements Comparable, Serializable {
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


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
