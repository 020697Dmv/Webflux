package com.practice.alumnos.infrastructure.exceptionhandler;

public enum ExceptionResponse{

    ALUMNO_ALREADY_EXISTS("El alumno ya existe"),
    ALUMNO_SAVE_ERROR("Error al guardar el alumno"),
    ALUMNO_NOT_FOUND("Alumno no encontrado con id: %s");
    private final String message;


    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getFormatMessage(RuntimeException runtimeException) {
        return String.format(this.message, runtimeException.getMessage());
    }
}
