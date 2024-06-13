package com.grupo01.clinica.service.impl;

import com.grupo01.clinica.domain.dtos.req.PrescriptionDTO;
import com.grupo01.clinica.domain.entities.Appointment;
import com.grupo01.clinica.domain.entities.Prescription;
import com.grupo01.clinica.repositorie.PrescriptionRepository;
import com.grupo01.clinica.service.contracts.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void savePrescription(PrescriptionDTO req, Appointment appointment) {
        Prescription prescription = modelMapper.map(req, Prescription.class);
        prescription.setAppointment(appointment);
        prescriptionRepository.save(prescription);
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Override
    public List<Prescription> savePrescriptionList(List<PrescriptionDTO> prescriptions, Appointment appointment) {
        List<Prescription> prescriptionList = new ArrayList<>();
        for (PrescriptionDTO prescriptionDTO : prescriptions) {
            Prescription prescription = modelMapper.map(prescriptionDTO, Prescription.class);
            prescription.setAppointment(appointment);
            prescriptionList = Collections.singletonList(prescription);
        }
        return prescriptionRepository.saveAll(prescriptionList);
    }
}
