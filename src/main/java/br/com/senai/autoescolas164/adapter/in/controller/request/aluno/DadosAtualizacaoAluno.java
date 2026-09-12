package br.com.senai.autoescolas164.adapter.in.controller.request.aluno;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;

public record DadosAtualizacaoAluno (
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco
        ){
}
