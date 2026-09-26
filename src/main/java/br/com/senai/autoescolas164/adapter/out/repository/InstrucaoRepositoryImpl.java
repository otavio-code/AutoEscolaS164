package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.InstrucaoEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.InstrucaoJpaRepository;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.port.out.InstrucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InstrucaoRepositoryImpl implements InstrucaoRepository {
    private final InstrucaoJpaRepository jpaRepository;
    private final InstrucaoEntityMapper entityMapper;

    @Override
    public Instrucao save(Instrucao instrucao) {
        InstrucaoEntity entity = entityMapper.toEntity(instrucao);
        InstrucaoEntity salvo = jpaRepository.save(entity);
        return entityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Instrucao> findById(Long id){
        return jpaRepository.findById(id).map(entityMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }

    @Override
    public Instrucao getReferenceById(Long id){
        InstrucaoEntity entity = jpaRepository.getReferenceById(id);
        return entityMapper.toDomain(entity);
    }
}
