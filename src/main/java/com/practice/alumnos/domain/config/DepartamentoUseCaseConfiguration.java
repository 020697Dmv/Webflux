package com.practice.alumnos.domain.config;

import com.practice.alumnos.application.handler.DepartamentoHandler;
import com.practice.alumnos.application.mapper.IDepartamentoMapper;
import com.practice.alumnos.application.mapper.IMessageReponseMapper;
import com.practice.alumnos.domain.api.IDepartamentoServicePort;
import com.practice.alumnos.domain.spi.IDepartamentoPersistencieport;
import com.practice.alumnos.domain.usecase.DepartamentoUseCase;
import com.practice.alumnos.infrastructure.out.jpa.adapter.DepartamentoAdapter;
import com.practice.alumnos.infrastructure.out.jpa.mapper.IDepartamentoDocumentMapper;
import com.practice.alumnos.infrastructure.out.jpa.repository.IDepartamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartamentoUseCaseConfiguration {


    @Bean
    public IDepartamentoPersistencieport departamentoPersistencieport(IDepartamentoRepository departamentoRepository,
                                                                      IDepartamentoDocumentMapper departamentoDocumentMapper){
        return new DepartamentoAdapter(departamentoRepository,departamentoDocumentMapper);
    }

    @Bean
    public IDepartamentoServicePort departamentoServicePort(IDepartamentoPersistencieport departamentoPersistencieport
                                                            ){
        return new DepartamentoUseCase(departamentoPersistencieport);
    }

    @Bean
    public DepartamentoHandler departamentoHandler(IDepartamentoServicePort departamentoServicePort, IDepartamentoMapper departamentoMapper,
                                                   IMessageReponseMapper messageReponseMapper                           ){
        return new DepartamentoHandler(departamentoServicePort, departamentoMapper,messageReponseMapper);
    }

}
