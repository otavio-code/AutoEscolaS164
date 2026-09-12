package br.com.senai.autoescolas164.adapter.in.controller.request.instrucao;

import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull
        @JsonProperty("id_aluno")
        //@JsonAlias("id_aluno") //Alternativa à anotação @JsonProperty
        Long idAluno,

        @JsonProperty("id_instrutor")
        Long idInstrutor,
        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        @JsonProperty("data_hora")
        LocalDateTime dataHora) {
}