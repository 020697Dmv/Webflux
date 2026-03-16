package com.practice.alumnos.infrastructure.out.jpa.mapper;

import com.practice.alumnos.domain.model.Departamento;
import com.practice.alumnos.infrastructure.out.jpa.documents.DepartamentoDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDepartamentoDocumentMapper {

    DepartamentoDocument toDepartamentoDocument(Departamento departamento);

     Departamento toDomainDepartamento(DepartamentoDocument departamentoDocument);
}
