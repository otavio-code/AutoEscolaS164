package br.com.senai.autoescolas164.application.core.specification;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.application.port.out.InstrucaoRepository;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorDisponibilidadeInstrutor implements ValidadorAgendamento {
    private final InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamento dados) {
        boolean ocupado = repository.existsByInstrutorIdAndDataHora(dados.idInstrutor(), dados.dataHora());

        if (ocupado) {
            throw new ValidacaoException("Instrutor ocupado na data/hora informada!");
        }
    }
}