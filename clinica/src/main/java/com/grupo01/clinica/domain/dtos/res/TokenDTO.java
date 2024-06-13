package com.grupo01.clinica.domain.dtos.res;

import com.grupo01.clinica.domain.entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token;

    public TokenDTO(Token token) {
        this.token = token.getContent();
    }
}
