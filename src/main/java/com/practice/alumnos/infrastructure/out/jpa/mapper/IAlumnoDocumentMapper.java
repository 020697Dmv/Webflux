package com.practice.alumnos.infrastructure.out.jpa.mapper;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.infrastructure.out.jpa.documents.AlumnoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import reactor.core.publisher.Flux;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAlumnoDocumentMapper {

    AlumnoDocumento toDocument(Alumno alumno);

    Alumno toDomain(AlumnoDocumento alumnoDocumento);

    default Flux<Alumno> toDomainFlux(Flux<AlumnoDocumento> alumnoDocumentos) {
        return alumnoDocumentos.map(this::toDomain);
    }
}
