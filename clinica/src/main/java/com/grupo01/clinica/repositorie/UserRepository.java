package com.grupo01.clinica.repositorie;

import com.grupo01.clinica.domain.entities.Role;
import com.grupo01.clinica.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);
    List<User>findAllByRoles(List<Role> roles);
}
