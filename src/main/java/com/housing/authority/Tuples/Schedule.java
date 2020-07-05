package com.housing.authority.Tuples;

import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "schedule")
@Data
@EqualsAndHashCode
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @Column(name = "scheduleid", nullable = false)
    private int scheduleid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeid", referencedColumnName = "employeeId")
    private Employees employees;
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

    @Basic
    @Column(name = "registerdate", length = 50, nullable = false)
    private String registerdate;

    @Basic
    @Column(name = "lastupdate", length = 50, nullable = false)
    private String lastupdate;


}
