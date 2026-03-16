package com.practice.alumnos.infrastructure.input.rest;

import com.practice.alumnos.application.dto.request.AlumnoRecord;
import com.practice.alumnos.application.dto.request.DepartamentoRecord;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.application.handler.impl.IDepartamentoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
public class DepartamentoController {

    private final IDepartamentoHandler departamentoHandler;

    @PostMapping("/departamentos")
    public Mono<StringResponseDto> crear(@RequestBody DepartamentoRecord departamentoRecord) {
        return departamentoHandler.saveDepartamento(departamentoRecord);
    }
}
