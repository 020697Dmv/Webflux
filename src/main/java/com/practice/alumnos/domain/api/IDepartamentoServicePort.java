package com.practice.alumnos.domain.api;

import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.domain.model.MessageResponse;
import reactor.core.publisher.Mono;

public interface IDepartamentoServicePort {

    Mono<MessageResponse>saveDepartamento(Departamento departamento);
}
