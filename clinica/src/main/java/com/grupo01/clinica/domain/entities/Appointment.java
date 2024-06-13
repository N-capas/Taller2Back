package com.grupo01.clinica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="medical_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String reason;
    private Date d_realization;
    private Date d_finalization;
    private Date d_request;
    private String status;
    private Date d_scheduling;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Attends> attends;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prescription> prescriptions;

}
