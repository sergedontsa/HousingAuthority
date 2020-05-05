package com.housing.authority.Repository;

import com.housing.authority.Tuples.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>
{
}
