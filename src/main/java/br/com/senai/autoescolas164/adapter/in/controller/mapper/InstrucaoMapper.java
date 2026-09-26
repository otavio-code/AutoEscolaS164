package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrucao.DadosDetalhamentoAgendamento;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrucaoMapper {
    public Instrucao toDomain(DadosAgendamento dados){
        return new Instrucao(
                null,
                dados.idAluno(),
                dados.idInstrutor(),
                dados.dataHora()
        );
    }

    public DadosDetalhamentoAgendamento toDetailDto(Instrucao instrucao){
        return new DadosDetalhamentoAluno(
                instrucao.getId(),
                instrucao.getAluno(),
                instrucao.getInstrutor(),
                instrucao.getDataHora()
        );
    }

}
