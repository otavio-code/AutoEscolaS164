package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InstrutorJpaRepository extends JpaRepository<InstrutorEntity, Long> {
    Page<InstrutorEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                SELECT i FROM Instrutor i
                WHERE
                i.ativo = TRUE
                AND
                i.especialidade = :especialidade
                AND
                i.id NOT IN(
                        SELECT a.instrutor.id FROM Instrucao a
                        WHERE
                        a.dataHora = :dataHora
                    )
                    ORDER BY rand()
                    LIMIT 1
            """)
    InstrutorEntity escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime dataHora);

    boolean existsByIdAndAtivoFalse(Long id);
}