package br.com.senai.autoescolas164.adapter.in.controller.request.instrutor;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public record DadosAtualizacaoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        DadosEndereco endereco) {
}