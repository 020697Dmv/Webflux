package com.practice.alumnos.domain.validacion;

import com.practice.alumnos.domain.model.Alumno;

import static com.practice.alumnos.domain.validacion.ValidationUtils.requiredValidarId;
import static com.practice.alumnos.domain.validacion.ValidationUtils.requiredValidarNombreApellido;

public class AlumnoValidation {

    private AlumnoValidation() {
    }

    public static void validateAlumno(final Alumno alumno){

        requiredValidarId(alumno.getId(),"ID");
        requiredValidarNombreApellido(alumno.getNombre(), "Nombre");
        requiredValidarNombreApellido(alumno.getApellido(), "Apellido");

    }
}
