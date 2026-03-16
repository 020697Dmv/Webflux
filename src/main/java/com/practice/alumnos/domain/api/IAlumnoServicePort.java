package com.practice.alumnos.domain.api;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAlumnoServicePort {

    Mono<MessageResponse> saveAlumno(Alumno alumno);

    Flux<Alumno> getAllAlumnosFindEstado(EstadoAlumno estado);

    Mono<Alumno> uptadeAlumno(String id, Alumno alumno);

    Mono<MessageResponse> deleteAlumno(String id);

}

