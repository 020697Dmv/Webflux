package com.practice.alumnos.application.handler.impl;

import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IAlumnoHandler {

    Mono<ResponseEntity<String>> saveAlumno(AlumnoRequestDto alumnoRequestDto);

    Flux<AlumnoResponseDto> getAllAlumnosFindEstado(EstadoAlumno estado);

}
