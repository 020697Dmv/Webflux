package com.practice.alumnos.domain.usecase;

import com.practice.alumnos.domain.api.IAlumnoServicePort;
import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IAlumnoPersistencePort;
import com.practice.alumnos.domain.validacion.AlumnoValidation;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AlumnoUseCase implements IAlumnoServicePort {

    private final IAlumnoPersistencePort alumnoPersistencePort;

    @Override
    public Mono<MessageResponse> saveAlumno(Alumno alumno) {
        AlumnoValidation.validateAlumno(alumno);
        return alumnoPersistencePort.saveAlumno(alumno);
    }

    @Override
    public Flux<Alumno> getAllAlumnosFindEstado(EstadoAlumno estado) {
        return alumnoPersistencePort.getAllAlumnosFindEstado(estado);
    }
}

