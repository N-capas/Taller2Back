package com.grupo01.clinica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="specialtys")
public class Specialty {

    @Id
    private String code;
    private String name;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Attends> attends;
}
