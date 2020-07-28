package com.housing.authority.Repository.Tenant;

import com.housing.authority.Tuples.Tenant.TenantSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantSubRepository extends JpaRepository<TenantSub, String> {
}
