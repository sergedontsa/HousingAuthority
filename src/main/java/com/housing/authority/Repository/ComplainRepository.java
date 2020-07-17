package com.housing.authority.Repository;

import com.housing.authority.Tuples.Complain.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends JpaRepository<Complain, String> {
}
