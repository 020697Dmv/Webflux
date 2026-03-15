package com.practice.alumnos.domain.usecase;

import com.practice.alumnos.domain.api.IDepartamentoServicePort;
import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IDepartamentoPersistencieport;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DepartamentoUseCase implements IDepartamentoServicePort {

    private final IDepartamentoPersistencieport departamentoPersistencePort;

    @Override
    public Mono<MessageResponse> saveDepartamento(Departamento departamento) {

        return departamentoPersistencePort.saveDepartamento(departamento);
    }
}
