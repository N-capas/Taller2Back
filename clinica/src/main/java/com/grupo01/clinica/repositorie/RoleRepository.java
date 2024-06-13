package com.grupo01.clinica.repositorie;

import com.grupo01.clinica.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String>{
    Role findByCode(String code);
}
