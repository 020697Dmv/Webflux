package com.practice.alumnos.application.mapper;

import com.practice.alumnos.application.dto.request.DepartamentoRecord;
import com.practice.alumnos.domain.model.Departamento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDepartamentoMapper {

    Departamento departamentoRecordTODepartamento(DepartamentoRecord departamentoRecord);

}
