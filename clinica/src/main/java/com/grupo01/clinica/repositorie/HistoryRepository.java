package com.grupo01.clinica.repositorie;

import com.grupo01.clinica.domain.entities.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<Historic, UUID> {
}
