package com.practice.alumnos.infrastructure.out.jpa.adapter;

import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IDepartamentoPersistencieport;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IDepartamentoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IDepartamentoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class Departamento  implements IDepartamentoPersistencieport {


    private final IDepartamentoRepository departamentoRepository;

    private final IDepartamentoDocumentMapper departamentoDocumentMapper;

    @Override
    public Mono<MessageResponse> saveDepartamento(com.practice.alumnos.domain.model.Departamento departamento) {
        return null;
    }
}
