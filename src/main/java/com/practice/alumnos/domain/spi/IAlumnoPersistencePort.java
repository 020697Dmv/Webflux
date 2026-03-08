package com.practice.alumnos.domain.spi;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IAlumnoPersistencePort {

    MessageResponse saveAlumno(Alumno alumno);

    Flux<Alumno> getAllAlumnosFindEstado(EstadoAlumno estado);
}
