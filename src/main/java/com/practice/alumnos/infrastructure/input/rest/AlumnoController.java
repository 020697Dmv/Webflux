package com.practice.alumnos.infrastructure.input.rest;

import com.practice.alumnos.application.dto.request.AlumnoRecord;
import com.practice.alumnos.application.dto.request.AlumnoUpdateRecord;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.application.handler.impl.IAlumnoHandler;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoHandler alumnoHandler;

    @GetMapping("/alumnos/estado")
    public Flux<AlumnoResponseDto> alumnos(@RequestParam("estadoAlumno") EstadoAlumno estadoAlumno) {
        return alumnoHandler.getAllAlumnosFindEstado(estadoAlumno);
    }

    @PostMapping("/alumnos")
    public Mono<StringResponseDto> crear(@RequestBody AlumnoRecord alumno) {
        return alumnoHandler.saveAlumno(alumno);
    }

    @PutMapping("alumnos/{id}")
    public Mono<AlumnoResponseDto> alumnosUpdate(@RequestBody AlumnoUpdateRecord alumnoUpdateRecord, @PathVariable String id) {
        return alumnoHandler.uptadeAlumno(id, alumnoUpdateRecord);
    }

    @DeleteMapping("alumnos/{id}")
    public Mono<StringResponseDto> deleteAlumno(@PathVariable String id) {
        return alumnoHandler.deleteAlumno(id);
    }
}

