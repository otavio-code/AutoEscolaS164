package br.com.senai.autoescolas164.adapter.in.controller.request.aluno;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno (

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,

    @Valid
    DadosEndereco endereco) {
}