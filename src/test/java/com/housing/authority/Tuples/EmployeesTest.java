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
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeesTest implements Service {

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
        List<Employees> employeesList = this.employeeRepository.findAll();
        assertThat(employeesList).size().isGreaterThan(0);

    }

    @Override
    @Order(2)
    @Test
    public void readOneTest() {
        boolean employeeIsPresent = this.employeeRepository.findById(this.employeeIdForTest).isPresent();
        assertTrue(employeeIsPresent);
        Employees existingEmployees = this.employeeRepository.findById(this.employeeIdForTest).get();
        assertNotNull(existingEmployees);
    }

    @Override
    @Order(3)
    @Test
    public void createTest() {
        Employees employees = new Employees();
        assertNotNull(this.employeeIDForCreate);
        employees.setEmployeeId(this.employeeIDForCreate);
        employees.setFirstName(this.firstName);
        employees.setMiddleName(this.middleName);
        employees.setLastName(this.lastName);
        employees.setDepartment(this.department);
        employees.setStatus(this.status);
        employees.setRegisterDate(this.registerDate);
        employees.setLastupdate(this.lastUpdate);
        Employees newEmployee = this.employeeRepository.save(employees);
        assertNotNull(newEmployee);
    }

    @Override
    @Order(4)
    @Test
    public void updateTest() {
        Employees existingEmployee = this.employeeRepository.getOne(this.employeeIdForTest);
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
        Employees updatedEmployee = this.employeeRepository.save(existingEmployee);
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