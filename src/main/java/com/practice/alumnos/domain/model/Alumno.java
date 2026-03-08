package com.practice.alumnos.domain.model;

import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Alumno {

    private String id;

    private String nombre;

    private String apellido;

    private EstadoAlumno estado;

    private Integer edad;
}
