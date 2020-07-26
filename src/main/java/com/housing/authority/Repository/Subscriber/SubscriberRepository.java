package com.housing.authority.Repository.Subscriber;

import com.housing.authority.Tuples.User.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
}
