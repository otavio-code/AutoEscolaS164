package br.com.senai.autoescolas164.adapter.in.controller.response.aluno;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco,
        boolean ativo) {
}
