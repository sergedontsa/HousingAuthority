package com.housing.authority.Tuples;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "building")
@Data
@EqualsAndHashCode
@Setter
@Getter
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
public class Building  implements Comparable, Serializable {

    @Id
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingId;
    @Basic
    @Column(name = "buildingnumber")
    private int buildingNumber;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "province")
    private String province;
    @Basic
    @Column(name = "zipcode")
    private String zipCode;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "numlevel")
    private int numLevel;
    @Basic
    @Column(name = "numbedroom")
    private int numBedRoom;
    @Basic
    @Column(name = "numbathroom")
    private int numBathRoom;
    @Basic
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @Basic
    @Column(name = "numkitchen")
    private int numKitchen;
    @Basic
    @Column(name = "numdoor")
    private int numDoor;
    @Basic
    @Column(name = "numwindows")
    private int numWindows;
    @Basic
    @Column(name = "numapartment")
    private int numApartment;
    @Basic
    @Column(name = "numstudio")
    private int numStudio;
    @Basic
    @Column(name = "numparkingspace")
    private int numParkingSpace;
    @Basic
    @Column(name = "iswithpool")
    private boolean isWithPool;
    @Basic
    @Column(name = "iswithelevator")
    private boolean isWithElevator;
    @Basic
    @Column(name = "registerdate")
    private String registerDate;

    @Basic
    @Column(name = "lastupdate")
    private String lastUpdate;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Apartment.class)
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Apartment.class)
    @JoinColumn(name = "apartmentid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Apartment> apartments;



    public boolean isWithPool() {
        return isWithPool;
    }

    public void setWithPool(boolean withPool) {
        isWithPool = withPool;
    }

    public boolean isWithElevator() {
        return isWithElevator;
    }

    public void setWithElevator(boolean withElevator) {
        isWithElevator = withElevator;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
