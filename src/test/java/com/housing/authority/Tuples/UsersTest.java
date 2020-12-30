package com.housing.authority.Tuples;

import com.housing.authority.Repository.User.UserRepository;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.User.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersTest implements Service {



    @BeforeEach
    void init(){

    }

    @Autowired
    UserRepository userRepository;

    @Test
    public void readAllTest() {
        List<Users> users = this.userRepository.findAll();
        assertThat(users).size().isGreaterThanOrEqualTo(0);
    }

    @Test
    public void readOneTest() {


    }

    @Test
    public void createTest() {


    }

    @Test
    public void updateTest() {

    }


    @Test
    public void deleteTest() {

    }
}