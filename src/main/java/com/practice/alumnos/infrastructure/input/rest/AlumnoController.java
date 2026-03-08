package com.practice.alumnos.infrastructure.input.rest;

import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.handler.impl.IAlumnoHandler;
import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoHandler alumnoHandler;

    @GetMapping("/obtenerAlumnosPorEstado")
    public Flux<AlumnoResponseDto> alumnos(@RequestParam("estadoAlumno") EstadoAlumno estadoAlumno) {
        return alumnoHandler.getAllAlumnosFindEstado(estadoAlumno);
    }

    @PostMapping("/guardarAlumno")
    public Mono<ResponseEntity<String>> crear(@RequestBody AlumnoRequestDto alumno) {
        return alumnoHandler.saveAlumno(alumno);
    }
}
