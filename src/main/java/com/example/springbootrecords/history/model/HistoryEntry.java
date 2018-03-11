package com.example.springbootrecords.history.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String patientDni;

    private String observations;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
