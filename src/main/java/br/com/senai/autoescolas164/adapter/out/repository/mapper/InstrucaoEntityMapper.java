package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import org.springframework.stereotype.Component;

@Component
public class InstrucaoEntityMapper {

    private final AlunoEntityMapper alunoEntityMapper;
    private final InstrutorEntityMapper instrutorEntityMapper;

    public InstrucaoEntityMapper(
            AlunoEntityMapper alunoEntityMapper,
            InstrutorEntityMapper instrutorEntityMapper) {

        this.alunoEntityMapper = alunoEntityMapper;
        this.instrutorEntityMapper = instrutorEntityMapper;
    }

    public InstrucaoEntity toEntity(Instrucao domain){
        return new InstrucaoEntity(
                domain.getId(),
                alunoEntityMapper.toEntity(domain.getAluno()),
                instrutorEntityMapper.toEntity(domain.getInstrutor()),
                domain.getDataHora()
        );
    }

    public Instrucao toDomain(InstrucaoEntity entity){
        return new Instrucao(
                entity.getId(),
                alunoEntityMapper.toDomain(entity.getAluno()),
                instrutorEntityMapper.toDomain(entity.getInstrutor()),
                entity.getDataHora()
        );
    }
}
