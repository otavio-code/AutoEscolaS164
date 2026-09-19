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
                //implementar
        );
    }

    public DadosDetalhamentoAgendamento toDetailDto(Instrucao instrucao){
        return new DadosDetalhamentoAluno(
                //implementar
        );
    }

}
