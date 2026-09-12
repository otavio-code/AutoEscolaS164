package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.AlunoJpaRepository;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.application.port.out.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlunoRepositoryImpl implements AlunoRepository {
    private final AlunoJpaRepository jpaRepository;
    private final AlunoEntityMapper entityMapper;

    @Override
    public Page<Aluno> findAllByAtivoTrue(Pageable paginacao){
      return jpaRepository.findAllByAtivoTrue(paginacao).map(entityMapper::toDomain);
    }

    @Override
    public boolean existsByIdAndAtivoFalse(Long id) {
        return jpaRepository.existsByIdAndAtivoFalse(id);
    }

    @Override
    public Aluno save(Aluno aluno) {
        AlunoEntity entity = entityMapper.toEntity(aluno);
        AlunoEntity salvo = jpaRepository.save(entity);
        return entityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Aluno> findById(Long id){
        return jpaRepository.findById(id).map(entityMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }

    @Override
    public Aluno getReferenceById(Long id){
        AlunoEntity entity = jpaRepository.getReferenceById(id);
        return entityMapper.toDomain(entity);
    }

}
