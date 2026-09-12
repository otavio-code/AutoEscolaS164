package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoJpaRepository {
    Page<AlunoEntity> findAllByAtivoTrue(Pageable paginacao);
    boolean existsByIdAndAtivoFalse(Long id);

}
