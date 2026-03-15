package com.practice.alumnos.application.handler.impl;

import com.practice.alumnos.application.dto.request.DepartamentoRecord;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface IDepartamentoHandler {

    Mono<ResponseEntity<StringResponseDto>> saveDepartamento(DepartamentoRecord departamentoRecord);

}
