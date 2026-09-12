package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import org.springframework.stereotype.Component;

@Component
public class InstrutorEntityMapper {
    public InstrutorEntity toEntity(Instrutor domain) {
        return new InstrutorEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getTelefone(),
                domain.getCnh(),
                domain.isAtivo(),
                domain.getEspecialidade(),
                domain.getEndereco()
        );
    }

    public Instrutor toDomain(InstrutorEntity entity) {
        return new Instrutor(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCnh(),
                entity.isAtivo(),
                entity.getEspecialidade(),
                entity.getEndereco()
        );
    }
}