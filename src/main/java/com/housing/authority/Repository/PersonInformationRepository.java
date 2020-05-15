package com.housing.authority.Repository;

import com.housing.authority.Tuples.Personinformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInformationRepository extends JpaRepository<Personinformation, String>
{
    @Query("SELECT CASE WHEN COUNT (p)> 0 THEN TRUE ELSE FALSE END FROM Personinformation p " +
            "WHERE p.personid=:personid OR p.phonenumber=:phonenumber OR p.email = :email OR p.idtype=:idtype" +
            " OR p.idnumber=:idnumber OR p.idissuedate=:idissuedate OR p.idexpireddate=:idexpireddate OR p.address=:address OR p.country=:country")
    boolean isRecordExist(@Param("personid")String personid,
                          @Param("phonenumber") String phonenumber,
                          @Param("email") String email,
                          @Param("idtype") String idtype,
                          @Param("idnumber") String idnumber,
                          @Param("idissuedate") String idissuedate,
                          @Param("idexpireddate") String idexpireddate,
                          @Param("address") String address,
                          @Param("country") String country);
}
