package com.practice.alumnos.application.dto.request;

import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;

public record AlumnoStatusRecord(EstadoAlumno estado) {
}
