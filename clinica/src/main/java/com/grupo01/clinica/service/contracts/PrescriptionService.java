package com.grupo01.clinica.service.contracts;

import com.grupo01.clinica.domain.dtos.req.PrescriptionDTO;
import com.grupo01.clinica.domain.entities.Appointment;
import com.grupo01.clinica.domain.entities.Prescription;

import java.util.List;

public interface PrescriptionService {
    void savePrescription(PrescriptionDTO req, Appointment appointment);
    List<Prescription>findAll();
    List<Prescription>savePrescriptionList(List<PrescriptionDTO> prescriptions, Appointment appointment);
}
