package com.practice.alumnos.application.mapper;

import com.practice.alumnos.application.dto.response.StringResponseDto;
import com.practice.alumnos.domain.model.MessageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMessageReponseMapper {

    StringResponseDto toResponse(MessageResponse messageResponse);

}
