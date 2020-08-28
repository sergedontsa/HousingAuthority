package com.housing.authority;


import com.housing.authority.Tuples.Apartment.ApartmentDocument;
import com.housing.authority.Tuples.Building.BuildingDocument;
import com.housing.authority.Tuples.Employee.EmployeeDocument;
import com.housing.authority.Tuples.Tenant.TenantDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableConfigurationProperties({
        EmployeeDocument.class,
        ApartmentDocument.class,
        TenantDocument.class,
        BuildingDocument.class
})
@ConfigurationPropertiesScan("com.housing.authority")
//@EnableJms
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class HousingAuthorityApplication {


    public static void main(String[] args) {
        SpringApplication.run(HousingAuthorityApplication.class, args);

    }




}
