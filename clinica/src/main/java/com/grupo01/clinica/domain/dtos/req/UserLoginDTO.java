package com.grupo01.clinica.domain.dtos.req;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
