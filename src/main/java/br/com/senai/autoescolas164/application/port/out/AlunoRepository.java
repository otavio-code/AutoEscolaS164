package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlunoRepository {
    boolean existsByIdAndAtivoFalse(Long id);

    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    Aluno save(Aluno aluno);

    Optional<Aluno> findById(Long id);

    Aluno getReferenceById(Long id);

    boolean existsById(Long id);
}