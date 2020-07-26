package com.housing.authority.Tuples.Complain;

import com.housing.authority.AuditModel.AuditModel;

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
public class Complaindone extends AuditModel implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
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


    //-------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfirmationid() {
        return confirmationid;
    }

    public void setConfirmationid(String confirmationid) {
        this.confirmationid = confirmationid;
    }

    public String getTimespend() {
        return timespend;
    }

    public void setTimespend(String timespend) {
        this.timespend = timespend;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }
}
