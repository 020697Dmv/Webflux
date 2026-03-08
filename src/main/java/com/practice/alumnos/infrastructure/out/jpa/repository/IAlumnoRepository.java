package com.practice.alumnos.infrastructure.out.jpa.repository;

import com.practice.alumnos.infrastructure.out.jpa.documents.AlumnoDocumento;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IAlumnoRepository extends ReactiveMongoRepository<AlumnoDocumento, String> {

    Flux<AlumnoDocumento> findByEstado(EstadoAlumno estado);
}

