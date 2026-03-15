package com.practice.alumnos.infrastructure.out.jpa.adapter;

import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.domain.model.MessageResponse;
import com.practice.alumnos.domain.spi.IDepartamentoPersistencieport;
import com.practice.alumnos.infrastructure.exception.AlumnoAlreadyExistsException;
import com.practice.alumnos.infrastructure.exception.AlumnoSaveException;
import com.practice.alumnos.infrastructure.out.jpa.documents.DepartamentoDocument;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IDepartamentoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IDepartamentoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DepartamentoAdapter implements IDepartamentoPersistencieport {


    private final IDepartamentoRepository departamentoRepository;
    private final IDepartamentoDocumentMapper departamentoDocumentMapper;

    @Override
    public Mono<MessageResponse> saveDepartamento(Departamento departamento) {
        DepartamentoDocument departamentoDocument = departamentoDocumentMapper.toDepartamentoDocument(departamento);

        return departamentoRepository.findById(departamento.getIdDepartamento())
                .flatMap(existente -> Mono.<MessageResponse>error(new AlumnoAlreadyExistsException()))
                .switchIfEmpty(
                        departamentoRepository.save(departamentoDocument)
                                .map(guardado -> new MessageResponse("Departamento guardado exitosamente", true))
                                .onErrorMap(AlumnoSaveException::new)
                );
    }
}
