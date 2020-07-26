package com.housing.authority.Tuples;

import com.housing.authority.Repository.User.UserRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.User.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersTest implements Service {

    private String idForTest;
    private String idForCreate;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private String registerDate;
    private String lastUpdate;

    @BeforeEach
    void init(){
        this.idForCreate = IDGenerator.USER_ID();
        this.idForTest = "158FLI2";
        this.username = "Username Test";
        this.phoneNumber = "4444444444";
        this.email = "testemail@gamil.com";
        this.password = "password";
        this.role = "Role";
        this.registerDate = Constant.getCurrentDateAsString();
        this.lastUpdate = Constant.getCurrentDateAsString();

    }

    @Autowired
    UserRepository userRepository;

    @Override
    @Test
    @Order(1)
    public void readAllTest() {
        List<Users> users = this.userRepository.findAll();
        assertThat(users).size().isGreaterThanOrEqualTo(0);
    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {
        Users users = this.userRepository.getOne(idForTest);
        assertNotNull(users);
        assertEquals(idForTest, users.getUserId());

    }

    @Override
    @Test
    @Order(3)
    public void createTest() {
        Users users = new Users();
        users.setUserId(idForCreate);
        users.setUsername(username);
        users.setPhonenumber(phoneNumber);
        users.setEmail(email);
        users.setPassword(password);
        users.setRole(role);
        users.setRegisterDate(registerDate);
        users.setLastUpdate(lastUpdate);
        Users savedUser = this.userRepository.save(users);
        assertNotNull(savedUser);

    }

    @Override
    @Test
    @Order(4)
    public void updateTest() {
        Users existingUser = this.userRepository.getOne(idForTest);
        assertNotNull(existingUser);
        assertEquals(idForTest, existingUser.getUserId());
        existingUser.setUsername(username);
        existingUser.setPhonenumber(phoneNumber);
        existingUser.setEmail(email);
        existingUser.setPassword(password);
        existingUser.setRole(role);
        existingUser.setRegisterDate(registerDate);
        existingUser.setLastUpdate(lastUpdate);

        Users updatedUser = this.userRepository.saveAndFlush(existingUser);
        assertNotNull(updatedUser);
    }

    @Override
    @Test
    @Order(5)
    public void deleteTest() {
        boolean isPresent = this.userRepository.findById(idForTest).isPresent();
        if (isPresent){
            assertNotNull(this.userRepository.findById(idForTest).get());
            this.userRepository.delete(this.userRepository.findById(this.idForTest).get());
            boolean isAbsent = this.userRepository.findById(idForTest).isPresent();
            assertFalse(isAbsent);
        }else {
            fail("Could not locate the entity for test");
        }
    }
}