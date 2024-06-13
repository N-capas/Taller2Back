package com.grupo01.clinica.service.contracts;

import com.grupo01.clinica.domain.dtos.req.AppointmentDTO;
import com.grupo01.clinica.domain.entities.Appointment;
import com.grupo01.clinica.domain.entities.Prescription;
import com.grupo01.clinica.domain.entities.User;
import com.grupo01.clinica.repositorie.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    void createAppointment(AppointmentDTO req, User user);
    List<Appointment>findAll();
  
    Appointment findById(UUID id);

    //void updateStatus(List<User> doctors, Appointment appointment, String status);

    List<Appointment> getAppointments(UUID id, String status);
    void finishAppointment(Appointment appointment);
    void savePrescriptions(List<Prescription> pres, Appointment appointment);
    //List<Appointment>findAllByUser(User user);


}
