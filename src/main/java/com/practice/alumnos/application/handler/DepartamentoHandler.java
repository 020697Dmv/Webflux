package com.practice.alumnos.application.handler;

import com.practice.alumnos.application.dto.request.DepartamentoRecord;
import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.application.handler.impl.IDepartamentoHandler;
import com.practice.alumnos.application.mapper.IDepartamentoMapper;
import com.practice.alumnos.application.mapper.IMessageReponseMapper;
import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.domain.api.IDepartamentoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DepartamentoHandler implements IDepartamentoHandler {

    public final IDepartamentoServicePort departamentoServicePort;
    public final IDepartamentoMapper departamentoMapper;
    public final IMessageReponseMapper messageReponseMapper;

    @Override
    public Mono<StringResponseDto> saveDepartamento(DepartamentoRecord departamentoRecord) {
        Departamento departamentoSave = departamentoMapper.departamentoRecordTODepartamento(departamentoRecord);

        return departamentoServicePort.saveDepartamento(departamentoSave)
                .map(messageReponseMapper::toResponse);
    }
}
