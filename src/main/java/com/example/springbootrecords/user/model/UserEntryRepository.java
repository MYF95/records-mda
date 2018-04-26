package com.example.springbootrecords.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntryRepository extends JpaRepository<UserEntry, Integer>{
    List<UserEntry> findAllByPatientDni(String patientDni);
}
