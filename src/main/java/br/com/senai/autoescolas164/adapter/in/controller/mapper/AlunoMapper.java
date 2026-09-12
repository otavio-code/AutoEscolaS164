package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.autoescolas164.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlunoMapper {
    private final EnderecoMapper enderecoMapper;
    private final AssertTrueValidator assertTrueValidator;

    public Aluno toDomain(DadosCadastroAluno dados){
        return new Aluno(
                null,
                dados.nome(),
                dados.email(),
                dados.telefone(),
                dados.cpf(),
                enderecoMapper.toEndereco(dados.endereco()),
                true
        );
    }

    public DadosDetalhamentoAluno toDetailDto(Aluno aluno){
        return new DadosDetalhamentoAluno(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                enderecoMapper.toDto(aluno.getEndereco()),
                aluno.isAtivo()
        );
    }

    public DadosListagemAluno toListDto(Aluno aluno){
        return new DadosListagemAluno(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail()
        );
    }
}
