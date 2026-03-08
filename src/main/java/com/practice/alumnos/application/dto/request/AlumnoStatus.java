package com.practice.alumnos.application.dto.request;

import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoStatus {

    private EstadoAlumno estado;
}
