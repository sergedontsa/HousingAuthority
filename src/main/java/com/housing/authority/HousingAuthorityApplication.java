package com.housing.authority;


import ch.qos.logback.classic.pattern.MessageConverter;
import com.housing.authority.Tuples.Apartment.ApartmentDocument;
import com.housing.authority.Tuples.Building.BuildingDocument;
import com.housing.authority.Tuples.Employee.EmployeeDocument;
import com.housing.authority.Tuples.Tenant.TenantDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;

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
@EnableJms
public class HousingAuthorityApplication {


    public static void main(String[] args) {
        SpringApplication.run(HousingAuthorityApplication.class, args);

    }




}
