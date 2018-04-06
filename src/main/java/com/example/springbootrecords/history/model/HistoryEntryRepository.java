package com.example.springbootrecords.history.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoryEntryRepository extends JpaRepository<HistoryEntry, Integer>{
    List<HistoryEntry> findAll();
    List<HistoryEntry> findAllByPatientDniOrderByDateDesc(String patientDni);
    List<HistoryEntry> findAllByDateIsGreaterThanEqual(Date date);
}
