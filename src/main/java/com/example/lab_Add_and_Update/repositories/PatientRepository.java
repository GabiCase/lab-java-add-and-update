package com.example.lab_Add_and_Update.repositories;

import com.example.lab_Add_and_Update.modules.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}

