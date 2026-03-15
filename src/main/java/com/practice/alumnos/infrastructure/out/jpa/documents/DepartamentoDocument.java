package com.practice.alumnos.infrastructure.out.jpa.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "departamento")
@NoArgsConstructor
public class Departamento {

    @Id
    private Long idDepartamento;

    private String nombreDepartamento;

    private String facultad;
}
