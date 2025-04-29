package com.example.lab_Add_and_Update.repositories;

import com.example.lab_Add_and_Update.modules.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

