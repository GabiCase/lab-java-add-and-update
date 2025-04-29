package com.example.lab_Add_and_Update.controllers;

import com.example.lab_Add_and_Update.modules.Employee;
import com.example.lab_Add_and_Update.modules.Patient;
import com.example.lab_Add_and_Update.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping
    public List<Employee> getAll(){
        return employeeRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee changeEmployee(@PathVariable Long id,@RequestBody Employee newEmployee){
        var employee = employeeRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employee.setStatus(newEmployee.getStatus());
        return employee;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee newEmployee){
        var employee = employeeRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employee.setDepartment(newEmployee.getDepartment());
        return employee;
    }

}
