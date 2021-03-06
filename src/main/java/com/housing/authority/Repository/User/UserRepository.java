package com.housing.authority.Repository.User;

import com.housing.authority.Tuples.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query("SELECT CASE WHEN COUNT(u)>0 THEN TRUE ELSE FALSE END FROM Users u WHERE u.username=:username OR u.email=:email OR u.phonenumber=:phonenumber")
    boolean isUserExist(@Param("username") String username, @Param("email")String email, @Param("phonenumber") String phonenumber);

}
