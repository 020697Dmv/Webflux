package com.practice.alumnos.infrastructure.out.jpa.repository;

import com.practice.alumnos.infrastructure.out.jpa.documents.DepartamentoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IDepartamentoRepository  extends ReactiveMongoRepository<DepartamentoDocument,Long> {


}
