package com.grupo01.clinica.repositorie;

import com.grupo01.clinica.domain.entities.Appointment;
import com.grupo01.clinica.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{

        List<Appointment> findByIdAndStatus(UUID id, String status);

        List<Appointment> findByUser(User user);
}
