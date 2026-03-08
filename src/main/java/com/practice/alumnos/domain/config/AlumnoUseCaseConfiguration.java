package com.practice.alumnos.domain.config;

import com.practice.alumnos.application.handler.AlumnoHandler;
import com.practice.alumnos.application.mapper.IAlumnoResponseMapper;
import com.practice.alumnos.application.mapper.IMessageReponseMapper;
import com.practice.alumnos.domain.api.IAlumnoServicePort;
import com.practice.alumnos.domain.spi.IAlumnoPersistencePort;
import com.practice.alumnos.domain.usecase.AlumnoUseCase;
import com.practice.alumnos.infrastructure.out.jpa.adapter.AlumnoAdapter;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IAlumnoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IAlumnoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlumnoUseCaseConfiguration {

    @Bean
    public IAlumnoPersistencePort alumnoPersistencePort(IAlumnoRepository alumnoRepository,
                                                        IAlumnoDocumentMapper alumnoDocumentMapper) {
        return new AlumnoAdapter(alumnoRepository, alumnoDocumentMapper);
    }

    @Bean
    public IAlumnoServicePort alumnoServicePort(IAlumnoPersistencePort alumnoPersistencePort) {
        return new AlumnoUseCase(alumnoPersistencePort);
    }

    @Bean
    public AlumnoHandler alumnoHandler(IAlumnoServicePort alumnoServicePort,
                                       IAlumnoResponseMapper alumnoResponseMapper,
                                       IMessageReponseMapper messageReponseMapper) {
        return new AlumnoHandler(alumnoServicePort, alumnoResponseMapper, messageReponseMapper);
    }
}
