package com.housing.authority.Tuples;

import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.Employee.Employee;
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
    private String department;
    private String status;


    @BeforeEach
    void init(){
        this.employeeIdForTest = "HAE-158031";
        this.employeeIDForCreate = IDGenerator.EMPLOYEE_ID();
         this.department = "Accounting Test";
        this.status = "Manager Test";

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

        employee.setDepartment(this.department);
        employee.setStatus(this.status);

        Employee newEmployee = this.employeeRepository.save(employee);
        assertNotNull(newEmployee);
    }

    @Override
    @Order(4)
    @Test
    public void updateTest() {
        Employee existingEmployee = this.employeeRepository.getOne(this.employeeIdForTest);
        assertNotNull(existingEmployee);

        assertNotEquals(existingEmployee.getDepartment(), this.department);
        assertNotEquals(existingEmployee.getStatus(), this.status);


        existingEmployee.setDepartment(this.department);
        existingEmployee.setStatus(this.status);

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