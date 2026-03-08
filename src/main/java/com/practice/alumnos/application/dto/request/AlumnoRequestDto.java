package com.practice.alumnos.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoRequestDto {

    private String id;

    private String nombre;

    private String apellido;

    private String estado;

    private Integer edad;
}
