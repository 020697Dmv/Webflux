package com.practice.alumnos.application.dto.response;

import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoResponseDto {

    private String id;

    private String nombre;

    private String apellido;

    private EstadoAlumno estado;

    private Integer edad;
}
