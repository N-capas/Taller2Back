package com.grupo01.clinica.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name="attends")
public class Attends {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Specialty specialty;

    @ManyToOne(fetch = FetchType.EAGER)
    private Appointment appointment;

}
