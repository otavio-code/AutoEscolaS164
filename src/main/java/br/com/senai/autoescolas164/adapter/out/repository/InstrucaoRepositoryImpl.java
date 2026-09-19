package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.InstrucaoJpaRepository;
import br.com.senai.autoescolas164.application.port.out.InstrucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrucaoRepositoryImpl implements InstrucaoRepository {
    private final InstrucaoJpaRepository jpaRepository;
    private final InstrucaoEntityMapper entityMapper;
}
