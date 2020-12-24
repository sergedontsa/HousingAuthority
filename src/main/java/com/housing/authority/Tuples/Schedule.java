package com.housing.authority.Tuples;


import com.housing.authority.AuditModel.AuditModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "schedule")
public class Schedule extends AuditModel implements Serializable {
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

    //----------------


    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
