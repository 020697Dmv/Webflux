package com.practice.alumnos.application.handler.impl;

import com.practice.alumnos.application.dto.request.AlumnoRecord;
import com.practice.alumnos.application.dto.request.AlumnoUpdateRecord;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IAlumnoHandler {

    Mono<StringResponseDto> saveAlumno(AlumnoRecord alumnoRecord);

    Flux<AlumnoResponseDto> getAllAlumnosFindEstado(EstadoAlumno estado);

    Mono<AlumnoResponseDto> uptadeAlumno(String id, AlumnoUpdateRecord alumnoUpdateRecord);

     Mono<StringResponseDto> deleteAlumno(String id);

}
