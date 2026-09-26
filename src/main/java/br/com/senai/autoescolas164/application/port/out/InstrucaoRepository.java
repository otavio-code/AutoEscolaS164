package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Instrucao;


import java.time.LocalDateTime;
import java.util.Optional;

public interface InstrucaoRepository {

    boolean existsByInstrutorIdAndDataHora(
            Long idInstrutor,
            LocalDateTime dataHora
    );

    boolean existsByAlunoIdAndDataHoraBetween(
            Long idAluno,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    Instrucao save(Instrucao instrucao);

    Optional<Instrucao> findById(Long id);

    boolean existsById(Long id);

    Instrucao getReferenceById(Long id);
}