package com.grupo01.clinica.repositorie;

import com.grupo01.clinica.domain.entities.Token;
import com.grupo01.clinica.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, String> {
    List<Token>findByUserAndActive(User user, Boolean active);
}
