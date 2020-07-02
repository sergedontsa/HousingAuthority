package com.housing.authority.Tuples;

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

@Entity(name = "subscriber")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Subscriber {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "registerdate", nullable = true, length = 50)
    private String registerdate;
    @Basic
    @Column(name = "lastupdate", nullable = true, length = 50)
    private String lastupdate;


    public Subscriber(String name, String email, String registerDate, String lastUpdate) {
        this.name = name;
        this.email = email;
        this.registerdate = registerDate;
        this.lastupdate = lastUpdate;
    }
}
