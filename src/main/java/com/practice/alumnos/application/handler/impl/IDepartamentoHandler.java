package com.practice.alumnos.application.handler.impl;

import com.practice.alumnos.application.dto.request.DepartamentoRecord;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface IDepartamento {

    Mono<ResponseEntity<StringResponseDto>> saveDepartamento(DepartamentoRecord departamentoRecord);

}
