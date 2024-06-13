package com.grupo01.clinica.domain.dtos.req;

import lombok.Data;

@Data
public class UserRegisterDTO {

    private String name;
    private String email;
    private String password;
}
