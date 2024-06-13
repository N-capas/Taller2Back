package com.grupo01.clinica.domain.dtos.req;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PrescriptionCreateDTO {

    List<PrescriptionDTO> prescriptions;
    private UUID appointment;

}
