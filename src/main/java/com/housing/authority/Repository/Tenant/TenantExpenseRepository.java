package com.housing.authority.Repository.Tenant;

import com.housing.authority.Tuples.Tenant.TenantExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantExpenseRepository extends JpaRepository<TenantExpense, String> {
}
