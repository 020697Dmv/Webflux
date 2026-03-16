package com.practice.alumnos.infrastructure.exception;

public class AlumnoSaveException extends RuntimeException {
    public AlumnoSaveException() {
        super();
    }

    public AlumnoSaveException(Throwable cause) {
        super(cause);
    }
}
