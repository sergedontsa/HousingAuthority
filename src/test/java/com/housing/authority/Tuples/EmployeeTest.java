package com.housing.authority.Tuples;

import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeTest implements Service {

    private String employeeIdForTest;
    private String employeeIDForCreate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String department;
    private String status;
    private String registerDate;
    private String lastUpdate;

    @BeforeEach
    void init(){
        this.employeeIdForTest = "HAE-158031";
        this.employeeIDForCreate = IDGenerator.EMPLOYEE_ID();
        this.firstName = "Doncfack Test";
        this.middleName = "Awoutsa Test";
        this.lastName = "Jacqueline Test";
        this.department = "Accounting Test";
        this.status = "Manager Test";
        this.registerDate = Constant.getCurrentDateAsString();
        this.lastUpdate = Constant.getCurrentDateAsString();
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Order(1)
    @Test
    public void readAllTest() {
        List<Employee> employeeList = this.employeeRepository.findAll();
        assertThat(employeeList).size().isGreaterThan(0);

    }

    @Override
    @Order(2)
    @Test
    public void readOneTest() {
        boolean employeeIsPresent = this.employeeRepository.findById(this.employeeIdForTest).isPresent();
        assertTrue(employeeIsPresent);
        Employee existingEmployee = this.employeeRepository.findById(this.employeeIdForTest).get();
        assertNotNull(existingEmployee);
    }

    @Override
    @Order(3)
    @Test
    public void createTest() {
        Employee employee = new Employee();
        assertNotNull(this.employeeIDForCreate);
        employee.setEmployeeId(this.employeeIDForCreate);
        employee.setFirstName(this.firstName);
        employee.setMiddleName(this.middleName);
        employee.setLastName(this.lastName);
        employee.setDepartment(this.department);
        employee.setStatus(this.status);
        employee.setRegisterDate(this.registerDate);
        employee.setLastupdate(this.lastUpdate);
        Employee newEmployee = this.employeeRepository.save(employee);
        assertNotNull(newEmployee);
    }

    @Override
    @Order(4)
    @Test
    public void updateTest() {
        Employee existingEmployee = this.employeeRepository.getOne(this.employeeIdForTest);
        assertNotNull(existingEmployee);
        assertNotEquals(existingEmployee.getFirstName(), this.firstName);
        assertNotEquals(existingEmployee.getMiddleName(), this.middleName);
        assertNotEquals(existingEmployee.getLastName(), this.lastName);
        assertNotEquals(existingEmployee.getDepartment(), this.department);
        assertNotEquals(existingEmployee.getStatus(), this.status);
        assertNotEquals(existingEmployee.getRegisterDate(), this.registerDate);
        assertNotEquals(existingEmployee.getLastName(), this.lastUpdate);

        existingEmployee.setFirstName(this.firstName);
        existingEmployee.setMiddleName(this.middleName);
        existingEmployee.setLastName(this.lastName);
        existingEmployee.setDepartment(this.department);
        existingEmployee.setStatus(this.status);
        existingEmployee.setRegisterDate(this.registerDate);
        existingEmployee.setLastupdate(this.lastUpdate);
        Employee updatedEmployee = this.employeeRepository.save(existingEmployee);
        assertNotNull(updatedEmployee);
    }

    @Override
    @Order(5)
    @Test
    public void deleteTest() {
        boolean isPresent = this.employeeRepository.findById(this.employeeIdForTest).isPresent();
        if (isPresent){
            assertTrue(isPresent);
            this.employeeRepository.delete(this.employeeRepository.findById(this.employeeIdForTest).get());
            boolean isAbsent = this.employeeRepository.findById(this.employeeIdForTest).isPresent();
            assertFalse(isAbsent);
        }else {
            assertFalse(isPresent);
            fail("Could not find the entity to test");
        }
    }
}