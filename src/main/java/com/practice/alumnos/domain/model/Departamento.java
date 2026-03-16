package com.practice.alumnos.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Departamento {


    private Long idDepartamento;

    private String nombreDepartamento;

    private String facultad;
}
