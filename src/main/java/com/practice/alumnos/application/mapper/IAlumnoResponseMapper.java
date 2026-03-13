package com.practice.alumnos.application.mapper;

import com.practice.alumnos.application.dto.request.AlumnoRecord;
import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.domain.model.Alumno;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import reactor.core.publisher.Flux;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAlumnoResponseMapper {

    AlumnoResponseDto toResponse(Alumno alumno);

    Alumno alumnoResponseDtoToAlumno(AlumnoRecord alumnoRecord);

    default Flux<AlumnoResponseDto> toResponseList(Flux<Alumno> alumnos) {
        return alumnos.map(this::toResponse);
    }

}
