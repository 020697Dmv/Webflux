package com.practice.alumnos.application.handler;

import com.practice.alumnos.application.dto.request.AlumnoRecord;
import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.application.handler.impl.IAlumnoHandler;
import com.practice.alumnos.application.mapper.IAlumnoResponseMapper;
import com.practice.alumnos.application.mapper.IMessageReponseMapper;
import com.practice.alumnos.domain.api.IAlumnoServicePort;
import com.practice.alumnos.domain.model.Alumno;
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
    public Mono<ResponseEntity<StringResponseDto>> saveAlumno(AlumnoRecord alumnoRecord) {
        Alumno alumnoSave = iAlumnoResponseMapper.alumnoResponseDtoToAlumno(alumnoRecord);

        return alumnoServicePort.saveAlumno(alumnoSave)
                .flatMap(resultado -> {
                    StringResponseDto response = messageReponseMapper.toResponse(resultado);
                    HttpStatus status = resultado.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
                    return Mono.just(ResponseEntity.status(status).<StringResponseDto>body(response));
                });
    }

    @Override
    public Flux<AlumnoResponseDto> getAllAlumnosFindEstado(EstadoAlumno estado) {
        Flux<Alumno> alumnos = alumnoServicePort.getAllAlumnosFindEstado(estado);
        return iAlumnoResponseMapper.toResponseList(alumnos);
    }
}

