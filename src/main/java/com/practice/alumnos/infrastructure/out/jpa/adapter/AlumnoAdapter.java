package com.practice.alumnos.infrastructure.out.jpa.adapter;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IAlumnoPersistencePort;
import com.practice.alumnos.infrastructure.exception.AlumnoAlreadyExistsException;
import com.practice.alumnos.infrastructure.exception.AlumnoNotFoundException;
import com.practice.alumnos.infrastructure.exception.AlumnoSaveException;
import com.practice.alumnos.infrastructure.out.jpa.documents.AlumnoDocumento;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IAlumnoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IAlumnoRepository;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AlumnoAdapter implements IAlumnoPersistencePort {

    private final IAlumnoRepository alumnoRepository;
    private final IAlumnoDocumentMapper alumnoDocumentMapper;

    @Override
    public Mono<MessageResponse> saveAlumno(Alumno alumno) {
        AlumnoDocumento documento = alumnoDocumentMapper.toDocument(alumno);

        return alumnoRepository.findById(alumno.getId())
                .flatMap(existente -> Mono.<MessageResponse>error(new AlumnoAlreadyExistsException()))
                .switchIfEmpty(
                        alumnoRepository.save(documento)
                                .map(guardado -> new MessageResponse("", true))
                                .onErrorMap(AlumnoSaveException::new)
                );
    }

    @Override
    public Flux<Alumno> getAllAlumnosFindEstado(EstadoAlumno estado) {
        Flux<AlumnoDocumento> alumnoDocumentos = alumnoRepository.findByEstado(estado);
        return alumnoDocumentMapper.toDomainFlux(alumnoDocumentos);
    }

    @Override
    public Mono<Alumno> uptadeAlumno(String id, Alumno alumno) {
        return alumnoRepository.findById(id)
                .flatMap(existingAlumno -> {
                    existingAlumno.setNombre(alumno.getNombre());
                    existingAlumno.setApellido(alumno.getApellido());
                    existingAlumno.setEstado(alumno.getEstado());
                    existingAlumno.setEdad(alumno.getEdad());
                    return alumnoRepository.save(existingAlumno)
                            .map(alumnoDocumentMapper::toDomain);
                })
                .switchIfEmpty(Mono.error(new AlumnoNotFoundException()));
    }

    @Override
    public Mono<MessageResponse> deleteAlumno(String id) {
        return alumnoRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.<MessageResponse>error(new AlumnoNotFoundException());
                    }
                    return alumnoRepository.deleteById(id)
                            .thenReturn(new MessageResponse("Alumno eliminado exitosamente", true));
                });
    }
}
