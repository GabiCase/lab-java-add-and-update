package com.example.lab_Add_and_Update.controllers;


import com.example.lab_Add_and_Update.modules.Patient;
import com.example.lab_Add_and_Update.repositories.EmployeeRepository;
import com.example.lab_Add_and_Update.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping
    public List<Patient> getAll(){
        return patientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return patientRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient){
        var doctor= employeeRepo.findById(patient.getAdmittedBy().getId());

        if(doctor.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not found with this ID");
        }
        patient.setAdmittedBy(doctor.get());

        return patientRepo.save(patient);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patientInfo) {
        if (!patientRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var doctor = employeeRepo.findById(patientInfo.getAdmittedBy().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not found"));

        patientInfo.setId(id);
        patientInfo.setAdmittedBy(doctor);

        return patientRepo.save(patientInfo);
    }




}

