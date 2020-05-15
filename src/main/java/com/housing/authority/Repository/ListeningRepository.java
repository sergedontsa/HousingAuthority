package com.housing.authority.Repository;

import com.housing.authority.Tuples.Listening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningRepository extends JpaRepository<Listening, String> {
}
