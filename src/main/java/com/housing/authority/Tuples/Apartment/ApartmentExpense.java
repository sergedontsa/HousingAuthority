package com.housing.authority.Tuples.Apartment;

import com.housing.authority.AuditModel.AuditModel;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "apartment_expense", schema = "housingauthority", catalog = "")
public class ApartmentExpense extends AuditModel implements Serializable, Comparable {
    @Id
    @Column(name = "expenseId", nullable = false, length = 50)
    private String expenseId;
    @Basic
    @Column(name = "employee_role", nullable = false, length = 50)
    private String employeeRole;
    @Basic
    @Column(name = "apartmentId", nullable = false, length = 50)
    private String apartmentId;
    @Basic
    @Column(name = "item", nullable = false, length = 50)
    private String item;
    @Basic
    @Column(name = "supplier", nullable = true, length = 50)
    private String supplier;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "unit_price", nullable = true, length = 50)
    private String unitPrice;
    @Basic
    @Column(name = "total_price", nullable = false, length = 50)
    private String totalPrice;
    @Basic
    @Column(name = "currency", nullable = true, length = 50)
    private String currency;
    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;



    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }


    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }


    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }


    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
