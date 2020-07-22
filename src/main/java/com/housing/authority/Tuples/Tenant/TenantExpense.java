package com.housing.authority.Tuples.Tenant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "tenant_expense", schema = "housingauthority", catalog = "")
public class TenantExpense extends AuditModel implements Serializable, Comparable {
    @Id
    @Column(name = "expenseid", nullable = false, length = 50)
    private String expenseId;

    @Basic
    @Column(name = "buildingid", nullable = false, length = 50)
    private String buildingId;

    @Basic
    @Column(name = "apartmentid", nullable = false, length = 50)
    private String apartmentId;

    @Basic
    @Column(name = "tenantid", insertable = false, updatable = false)
    private String tenantid;

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
    @JoinColumn(name = "tenantid", nullable = false)
    @JsonBackReference("tenant_expense")
    private Tenant tenant;



    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
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
