package com.practice.alumnos.infrastructure.exceptionhandler;

import com.practice.alumnos.infrastructure.exception.AlumnoAlreadyExistsException;
import com.practice.alumnos.infrastructure.exception.AlumnoNotFoundException;
import com.practice.alumnos.infrastructure.exception.AlumnoSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(AlumnoAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlumnoAlreadyExistsException(
            AlumnoAlreadyExistsException ignoredAlumnoAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALUMNO_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(AlumnoSaveException.class)
    public ResponseEntity<Map<String, String>> handleAlumnoSaveException(
            AlumnoSaveException ignoredAlumnoSaveException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALUMNO_SAVE_ERROR.getMessage()));
    }

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAlumnoNotFoundException(
            AlumnoNotFoundException alumnoNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALUMNO_NOT_FOUND
                        .getFormatMessage(alumnoNotFoundException)));
    }

}
