package com.practice.alumnos.domain.validacion;

import com.practice.alumnos.domain.exception.DomainExcepcion;

import java.util.regex.Pattern;

public class ValidationUtils {

    private ValidationUtils() {

    }

    private static final String DOCUMENTO_PATRON =
            "^[0-9]+$";

    private static final Pattern document_valid =
            Pattern.compile(DOCUMENTO_PATRON);

    private static final String NOMBRE_PATRON =
            "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$";

    private static final Pattern nombre_valid =
            Pattern.compile(NOMBRE_PATRON);


        public static String requiredValidarId(String id, String nombreCampo) {
        if (id == null || id.isBlank()) {
                throw new DomainExcepcion(nombreCampo + " es requerido.");
        }
        return id;
        }


        public static String requiredValidarNombreApellido(String nombre, String nombreCampo) {
        
        if (nombre == null || nombre.isBlank()) {
                throw new DomainExcepcion(nombreCampo + " es requerido.");
        }
        
        if (!nombre_valid.matcher(nombre).matches()) {
                throw new DomainExcepcion(nombreCampo + " no tiene un formato válido (solo letras).");
        }
        
        return nombre;
        }



}
