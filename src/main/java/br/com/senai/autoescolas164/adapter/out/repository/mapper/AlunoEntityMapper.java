package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoEntityMapper {
    public AlunoEntity toEntity(Aluno domain){
        return new AlunoEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getTelefone(),
                domain.getCpf(),
                domain.getEndereco(),
                domain.isAtivo()
        );
    }

    public Aluno toDomain(AlunoEntity entity){
        return new Aluno(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCpf(),
                entity.getEndereco(),
                entity.isAtivo()
        );
    }
}
