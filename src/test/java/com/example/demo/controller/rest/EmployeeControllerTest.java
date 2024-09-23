package com.example.demo.controller.rest;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testImportEmployee() throws IOException {
        // Prepare test data
        String csvContent = "First Name,Birth Date\nJohn,1985-05-15\nJane,1990-08-25";
        MockMultipartFile file = new MockMultipartFile("file", "employees.csv", "text/csv", csvContent.getBytes());

        // Call the method under test
        String result = employeeController.importEmployee(file);

        // Verify the results
        assertEquals("Imported 2 rows.", result);

        // Check the database
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(2, employees.size());
        assertEquals("John", employees.get(0).getFirstName());
        assertEquals("1985-05-15", employees.get(0).getBirthDate().toString());
        assertEquals("Jane", employees.get(1).getFirstName());
        assertEquals("1990-08-25", employees.get(1).getBirthDate().toString());
    }
}