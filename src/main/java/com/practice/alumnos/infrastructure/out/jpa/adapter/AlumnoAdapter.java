package com.practice.alumnos.infrastructure.out.jpa.adapter;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IAlumnoPersistencePort;
import com.practice.alumnos.infrastructure.out.jpa.documents.AlumnoDocumento;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IAlumnoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IAlumnoRepository;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class AlumnoAdapter implements IAlumnoPersistencePort {

    private final IAlumnoRepository alumnoRepository;
    private final IAlumnoDocumentMapper alumnoDocumentMapper;

    @Override
    public MessageResponse saveAlumno(Alumno alumno) {
        AlumnoDocumento documento = alumnoDocumentMapper.toDocument(alumno);
        AlumnoDocumento guardado = alumnoRepository.save(documento).block();
        if (guardado != null) {
            return new MessageResponse("");
        }
        return new MessageResponse("Error al guardar el alumno");
    }

    @Override
    public Flux<Alumno> getAllAlumnosFindEstado(EstadoAlumno estado) {
        Flux<AlumnoDocumento> alumnoDocumentos =
                alumnoRepository.findByEstado(estado);
        return alumnoDocumentMapper.toDomainFlux(alumnoDocumentos);
    }
}
