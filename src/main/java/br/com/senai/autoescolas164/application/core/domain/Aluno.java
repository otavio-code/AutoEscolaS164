package br.com.senai.autoescolas164.application.core.domain;

import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;


public class Aluno {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    private Endereco endereco;
    private boolean ativo;

    public Aluno() {
    }

    
}