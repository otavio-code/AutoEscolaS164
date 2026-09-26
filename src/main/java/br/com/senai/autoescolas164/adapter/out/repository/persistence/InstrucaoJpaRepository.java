package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InstrucaoJpaRepository
        extends JpaRepository<InstrucaoEntity, Long> {

    boolean existsByInstrutorIdAndDataHora(
            Long idInstrutor,
            LocalDateTime dataHora
    );

    boolean existsByAlunoIdAndDataHoraBetween(
            Long alunoId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
