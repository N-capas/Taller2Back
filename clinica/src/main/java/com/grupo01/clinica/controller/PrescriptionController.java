package com.grupo01.clinica.controller;

import com.grupo01.clinica.domain.dtos.req.PrescriptionDTO;
import com.grupo01.clinica.domain.dtos.res.GeneralResponse;
import com.grupo01.clinica.domain.entities.Appointment;
import com.grupo01.clinica.service.contracts.AppointmentService;
import com.grupo01.clinica.service.contracts.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final AppointmentService appointmentService;

    public PrescriptionController(PrescriptionService prescriptionService, AppointmentService appointmentService) {
        this.prescriptionService = prescriptionService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<GeneralResponse> savePrescription(@RequestBody PrescriptionDTO req){
       Appointment appointment = appointmentService.findById(req.getAppointment());
       if(appointment == null){
              return GeneralResponse.getResponse(HttpStatus.FOUND, "Appointment not found!");
       }
         prescriptionService.savePrescription(req, appointment);

        return GeneralResponse.getResponse(HttpStatus.OK, "Prescription saved!");
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllPrescriptions(){
        try {
            return GeneralResponse.getResponse(HttpStatus.OK, prescriptionService.findAll());
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
