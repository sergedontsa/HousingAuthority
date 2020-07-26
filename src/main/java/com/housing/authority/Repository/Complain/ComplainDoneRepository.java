package com.housing.authority.Repository.Complain;

import com.housing.authority.Tuples.Complain.Complaindone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainDoneRepository extends JpaRepository<Complaindone, Integer>{}
