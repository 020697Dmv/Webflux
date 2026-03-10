package com.practice.alumnos.infrastructure.out.jpa;

import com.practice.alumnos.domain.model.Alumno;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.infrastructure.out.jpa.adapter.AlumnoAdapter;
import com.practice.alumnos.infrastructure.out.jpa.documents.AlumnoDocumento;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IAlumnoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IAlumnoRepository;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlumnoAdapterTest {

    private AlumnoAdapter alumnoAdapter;
    private IAlumnoRepository alumnoRepository;
    private IAlumnoDocumentMapper alumnoDocumentMapper;

    @BeforeEach
    void setUp() {
        // Mocks manuales — mismo patrón del ejemplo
        alumnoRepository    = mock(IAlumnoRepository.class);
        alumnoDocumentMapper = mock(IAlumnoDocumentMapper.class);
        alumnoAdapter        = new AlumnoAdapter(alumnoRepository, alumnoDocumentMapper);
    }

    // ═══════════════════════════════════════════════════════════
    //  saveAlumno — alumno nuevo (no existe en BD)
    // ═══════════════════════════════════════════════════════════

    @Test
    @DisplayName("saveAlumno - guarda exitosamente cuando el alumno NO existe")
    void saveAlumno_cuandoNoExiste_retornaMensajeExitoso() {
        Alumno alumno = crearAlumno("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);
        AlumnoDocumento documento = crearDocumento("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);

        when(alumnoDocumentMapper.toDocument(alumno)).thenReturn(documento);
        when(alumnoRepository.findById("1")).thenReturn(Mono.empty());       // no existe
        when(alumnoRepository.save(documento)).thenReturn(Mono.just(documento));

        Mono<MessageResponse> resultado = alumnoAdapter.saveAlumno(alumno);

        StepVerifier.create(resultado)
                .expectNextMatches(response ->
                        response.isSuccess() &&
                        response.getMessage().isEmpty())
                .verifyComplete();

        verify(alumnoRepository).findById("1");
        verify(alumnoRepository).save(documento);
    }

    @Test
    @DisplayName("saveAlumno - retorna mensaje de error cuando el alumno YA existe")
    void saveAlumno_cuandoYaExiste_retornaMensajeAlumnoYaExiste() {
        Alumno alumno = crearAlumno("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);
        AlumnoDocumento documentoExistente = crearDocumento("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);

        when(alumnoDocumentMapper.toDocument(alumno)).thenReturn(documentoExistente);
        when(alumnoRepository.findById("1")).thenReturn(Mono.just(documentoExistente)); // ya existe
        // switchIfEmpty evalúa su argumento de forma eager en Java (aunque no lo suscriba),
        // por eso debemos mockear save() para que no devuelva null al construir el pipeline.
        when(alumnoRepository.save(documentoExistente)).thenReturn(Mono.just(documentoExistente));

        Mono<MessageResponse> resultado = alumnoAdapter.saveAlumno(alumno);

        StepVerifier.create(resultado)
                .expectNextMatches(response ->
                        !response.isSuccess() &&
                        response.getMessage().equals("El alumno ya existe"))
                .verifyComplete();

        verify(alumnoRepository).findById("1");
    }

    @Test
    @DisplayName("saveAlumno - retorna mensaje de error cuando el repositorio lanza excepción")
    void saveAlumno_cuandoRepositorioFalla_retornaMensajeError() {
        Alumno alumno = crearAlumno("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);
        AlumnoDocumento documento = crearDocumento("1", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);

        when(alumnoDocumentMapper.toDocument(alumno)).thenReturn(documento);
        when(alumnoRepository.findById("1")).thenReturn(Mono.empty());
        when(alumnoRepository.save(documento)).thenReturn(Mono.error(new RuntimeException("Error de BD")));

        Mono<MessageResponse> resultado = alumnoAdapter.saveAlumno(alumno);

        StepVerifier.create(resultado)
                .expectNextMatches(response ->
                        !response.isSuccess() &&
                        response.getMessage().equals("Error al guardar el alumno"))
                .verifyComplete();

        verify(alumnoRepository).findById("1");
        verify(alumnoRepository).save(documento);
    }

    // ═══════════════════════════════════════════════════════════
    //  getAllAlumnosFindEstado
    // ═══════════════════════════════════════════════════════════

    @Test
    @DisplayName("getAllAlumnosFindEstado - retorna Flux con alumnos ACTIVOS")
    void getAllAlumnosFindEstado_retornaAlumnosActivos() {
        AlumnoDocumento doc1 = crearDocumento("1", "Juan",  "Pérez", EstadoAlumno.ACTIVO, 20);
        AlumnoDocumento doc2 = crearDocumento("2", "María", "López", EstadoAlumno.ACTIVO, 22);

        Alumno alumno1 = crearAlumno("1", "Juan",  "Pérez", EstadoAlumno.ACTIVO, 20);
        Alumno alumno2 = crearAlumno("2", "María", "López", EstadoAlumno.ACTIVO, 22);

        when(alumnoRepository.findByEstado(EstadoAlumno.ACTIVO))
                .thenReturn(Flux.just(doc1, doc2));
        when(alumnoDocumentMapper.toDomainFlux(any()))
                .thenReturn(Flux.just(alumno1, alumno2));

        Flux<Alumno> resultado = alumnoAdapter.getAllAlumnosFindEstado(EstadoAlumno.ACTIVO);

        StepVerifier.create(resultado)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();

        verify(alumnoRepository).findByEstado(EstadoAlumno.ACTIVO);
    }

    @Test
    @DisplayName("getAllAlumnosFindEstado - retorna Flux vacío cuando no hay alumnos")
    void getAllAlumnosFindEstado_retornaFluxVacio() {
        when(alumnoRepository.findByEstado(EstadoAlumno.INACTIVO))
                .thenReturn(Flux.empty());
        when(alumnoDocumentMapper.toDomainFlux(any()))
                .thenReturn(Flux.empty());

        Flux<Alumno> resultado = alumnoAdapter.getAllAlumnosFindEstado(EstadoAlumno.INACTIVO);

        StepVerifier.create(resultado)
                .verifyComplete();

        verify(alumnoRepository).findByEstado(EstadoAlumno.INACTIVO);
    }

    // ═══════════════════════════════════════════════════════════
    //  Helpers
    // ═══════════════════════════════════════════════════════════

    private Alumno crearAlumno(String id, String nombre, String apellido,
                               EstadoAlumno estado, int edad) {
        Alumno alumno = new Alumno();
        alumno.setId(id);
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setEstado(estado);
        alumno.setEdad(edad);
        return alumno;
    }

    private AlumnoDocumento crearDocumento(String id, String nombre, String apellido,
                                           EstadoAlumno estado, int edad) {
        AlumnoDocumento doc = new AlumnoDocumento();
        doc.setId(id);
        doc.setNombre(nombre);
        doc.setApellido(apellido);
        doc.setEstado(estado);
        doc.setEdad(edad);
        return doc;
    }
}
