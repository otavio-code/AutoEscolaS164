package br.com.senai.autoescolas164.application.core.domain;

import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public class Instrutor {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnh;
    private boolean ativo = true;
    private Especialidade especialidade;
    private Endereco endereco;

    public Instrutor() {
    }

    public Instrutor(
            Long id,
            String nome,
            String email,
            String telefone,
            String cnh,
            boolean ativo,
            Especialidade especialidade,
            Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnh = cnh;
        this.ativo = ativo;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCnh() {
        return cnh;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void atualizar(
            String nome,
            String email,
            String telefone,
            Especialidade especialidade,
            Endereco endereco) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
        if (telefone != null && !telefone.isBlank()) {
            this.telefone = telefone;
        }
        if (especialidade != null) {
            this.especialidade = especialidade;
        }
        if (endereco != null) {
            this.endereco.atualizar(
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getUf(),
                    endereco.getCep()
            );
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}