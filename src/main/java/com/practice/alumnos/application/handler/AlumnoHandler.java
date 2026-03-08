package com.practice.alumnos.application.handler;

import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.handler.impl.IAlumnoHandler;
import com.practice.alumnos.application.mapper.IAlumnoResponseMapper;
import com.practice.alumnos.application.mapper.IMessageReponseMapper;
import com.practice.alumnos.domain.api.IAlumnoServicePort;
import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AlumnoHandler implements IAlumnoHandler {

    public final IAlumnoServicePort alumnoServicePort;
    public final IAlumnoResponseMapper iAlumnoResponseMapper;
    public final IMessageReponseMapper messageReponseMapper;

    @Override
    public Mono<ResponseEntity<String>> saveAlumno(AlumnoRequestDto alumnoRequestDto) {
        Alumno alumnoSave = iAlumnoResponseMapper.alumnoResponseDtoToAlumno(alumnoRequestDto);
        MessageResponse resultado = alumnoServicePort.saveAlumno(alumnoSave);
        if (resultado != null && resultado.getMessage() != null && resultado.getMessage().startsWith("Error")) {
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado.getMessage()));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(""));
    }

    @Override
    public Flux<AlumnoResponseDto> getAllAlumnosFindEstado(EstadoAlumno estado) {
        Flux<Alumno> alumnos = alumnoServicePort.getAllAlumnosFindEstado(estado);
        return iAlumnoResponseMapper.toResponseList(alumnos);
    }
}
