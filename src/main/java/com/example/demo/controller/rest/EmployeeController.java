package com.example.demo.controller.rest;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public String importEmployee(@RequestParam("file") MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());

        // CSV conversion
        CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                .withType(Employee.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        // CSV parsing
        List<Employee> employees = csvToBean.parse();

        employeeRepository.saveAll(employees);

        return "Imported " + employees.size() + " rows.";
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
