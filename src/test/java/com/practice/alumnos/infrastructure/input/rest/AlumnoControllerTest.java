package com.practice.alumnos.infrastructure.input.rest;

import com.practice.alumnos.application.dto.request.AlumnoRequestDto;
import com.practice.alumnos.application.dto.response.AlumnoResponseDto;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.application.handler.impl.IAlumnoHandler;
import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlumnoControllerTest {

    private WebTestClient webTestClient;
    private IAlumnoHandler alumnoHandler;

    private AlumnoResponseDto alumnoActivo;
    private AlumnoResponseDto alumnoInactivo;

    @BeforeEach
    void setUp() {
        // Crear mock manualmente y enlazar WebTestClient al controlador
        alumnoHandler = mock(IAlumnoHandler.class);
        webTestClient = WebTestClient.bindToController(new AlumnoController(alumnoHandler)).build();

        // Datos de prueba
        alumnoActivo = new AlumnoResponseDto();
        alumnoActivo.setId("1");
        alumnoActivo.setNombre("Juan");
        alumnoActivo.setApellido("Pérez");
        alumnoActivo.setEstado(EstadoAlumno.ACTIVO);
        alumnoActivo.setEdad(20);

        alumnoInactivo = new AlumnoResponseDto();
        alumnoInactivo.setId("2");
        alumnoInactivo.setNombre("María");
        alumnoInactivo.setApellido("López");
        alumnoInactivo.setEstado(EstadoAlumno.INACTIVO);
        alumnoInactivo.setEdad(22);
    }

    // ═══════════════════════════════════════════════════════════
    //  GET  /v1/api/alumnos/estado
    // ═══════════════════════════════════════════════════════════

    @Test
    @DisplayName("GET /alumnos/estado - retorna lista de alumnos ACTIVOS")
    void testGetAlumnosPorEstadoActivo() {
        when(alumnoHandler.getAllAlumnosFindEstado(EstadoAlumno.ACTIVO))
                .thenReturn(Flux.just(alumnoActivo, alumnoInactivo));

        webTestClient.get()
                .uri("/v1/api/alumnos/estado?estadoAlumno=ACTIVO")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].nombre").isEqualTo("Juan")
                .jsonPath("$[0].estado").isEqualTo("ACTIVO")
                .jsonPath("$[1].nombre").isEqualTo("María")
                .jsonPath("$[1].estado").isEqualTo("INACTIVO");

        verify(alumnoHandler).getAllAlumnosFindEstado(EstadoAlumno.ACTIVO);
    }

    @Test
    @DisplayName("GET /alumnos/estado - retorna lista de alumnos INACTIVOS")
    void testGetAlumnosPorEstadoInactivo() {
        when(alumnoHandler.getAllAlumnosFindEstado(EstadoAlumno.INACTIVO))
                .thenReturn(Flux.just(alumnoInactivo));

        webTestClient.get()
                .uri("/v1/api/alumnos/estado?estadoAlumno=INACTIVO")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].nombre").isEqualTo("María")
                .jsonPath("$[0].estado").isEqualTo("INACTIVO")
                .jsonPath("$[0].apellido").isEqualTo("López");

        verify(alumnoHandler).getAllAlumnosFindEstado(EstadoAlumno.INACTIVO);
    }

    @Test
    @DisplayName("GET /alumnos/estado - retorna lista vacía cuando no hay alumnos")
    void testGetAlumnosPorEstado_ListaVacia() {
        when(alumnoHandler.getAllAlumnosFindEstado(EstadoAlumno.ACTIVO))
                .thenReturn(Flux.empty());

        webTestClient.get()
                .uri("/v1/api/alumnos/estado?estadoAlumno=ACTIVO")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(0);

        verify(alumnoHandler).getAllAlumnosFindEstado(EstadoAlumno.ACTIVO);
    }

    // ═══════════════════════════════════════════════════════════
    //  POST  /v1/api/alumnos
    // ═══════════════════════════════════════════════════════════

    @Test
    @DisplayName("POST /alumnos - guarda un alumno y retorna 201 CREATED")
    void testCrearAlumno_Exitoso() {
        when(alumnoHandler.saveAlumno(any(AlumnoRequestDto.class)))
                .thenReturn(Mono.just(
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new StringResponseDto("Alumno guardado exitosamente"))
                ));

        webTestClient.post()
                .uri("/v1/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                          "id": "1",
                          "nombre": "Juan",
                          "apellido": "Pérez",
                          "estado": "ACTIVO",
                          "edad": 20
                        }
                        """)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Alumno guardado exitosamente");

        verify(alumnoHandler).saveAlumno(any(AlumnoRequestDto.class));
    }

    @Test
    @DisplayName("POST /alumnos - retorna 400 cuando el alumno ya existe")
    void testCrearAlumno_YaExiste() {
        when(alumnoHandler.saveAlumno(any(AlumnoRequestDto.class)))
                .thenReturn(Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new StringResponseDto("El alumno ya existe"))
                ));

        webTestClient.post()
                .uri("/v1/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                          "id": "1",
                          "nombre": "Juan",
                          "apellido": "Pérez",
                          "estado": "ACTIVO",
                          "edad": 20
                        }
                        """)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message").isEqualTo("El alumno ya existe");

        verify(alumnoHandler).saveAlumno(any(AlumnoRequestDto.class));
    }
}
