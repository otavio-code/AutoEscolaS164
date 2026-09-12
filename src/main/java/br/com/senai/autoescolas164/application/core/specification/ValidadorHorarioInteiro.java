package br.com.senai.autoescolas164.application.core.specification;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioInteiro implements ValidadorAgendamento {
    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime agendada = dados.dataHora();

        if (agendada.getMinute() != 0 /*|| agendada.getSecond() != 0 || agendada.getNano() != 0*/) {
            throw new ValidacaoException("O horário deve ser preenchido em horas inteiras (ex: 08:00, 13:00, ...)");
        }
    }
}