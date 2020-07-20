package com.housing.authority.Tuples.Tenant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import com.housing.authority.Tuples.Apartment.Apartment;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tenant_expense", schema = "housingauthority", catalog = "")
public class TenantExpense extends AuditModel implements Serializable, Comparable {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenantId", nullable = false)
    @JsonBackReference("tenant_expense")
    private Tenant tenant;



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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
