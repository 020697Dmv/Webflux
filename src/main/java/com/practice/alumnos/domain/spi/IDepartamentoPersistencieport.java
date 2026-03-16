package com.practice.alumnos.domain.spi;

import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.domain.model.MessageResponse;
import reactor.core.publisher.Mono;

public interface IDepartamentoPersistencieport {

    Mono<MessageResponse> saveDepartamento(Departamento departamento);

}
