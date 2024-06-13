package com.grupo01.clinica.domain.dtos.req;

import com.grupo01.clinica.domain.entities.Appointment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PrescriptionDTO {

    private String medicine;
    private String dosage;
    private Date d_finalization;
    private UUID appointment;
}
