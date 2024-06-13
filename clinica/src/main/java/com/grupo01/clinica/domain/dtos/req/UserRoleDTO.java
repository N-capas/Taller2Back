package com.grupo01.clinica.domain.dtos.req;

import com.grupo01.clinica.domain.entities.Role;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserRoleDTO {
    private String idRole;
    private String email;

}
