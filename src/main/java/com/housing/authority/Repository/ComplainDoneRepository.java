package com.housing.authority.Repository;

import com.housing.authority.Tuples.Complaindone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainDoneRepository extends JpaRepository<Complaindone, Integer>{}
