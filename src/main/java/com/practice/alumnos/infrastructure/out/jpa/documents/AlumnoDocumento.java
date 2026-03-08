package com.practice.alumnos.infrastructure.out.jpa.documents;

import com.practice.alumnos.infrastructure.out.jpa.util.EstadoAlumno;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "alumnos")
@NoArgsConstructor
public class AlumnoDocumento {

    @Id
    private String id;

    private String nombre;

    private String apellido;

    private EstadoAlumno estado;

    private Integer edad;

}
