package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrutorMapper {
    private final EnderecoMapper enderecoMapper;

    public Instrutor toDomain(DadosCadastroInstrutor dados) {
        return new Instrutor(
                null,
                dados.nome(),
                dados.email(),
                dados.telefone(),
                dados.cnh(),
                true,
                dados.especialidade(),
                enderecoMapper.toEndereco(dados.endereco())
        );
    }

    public DadosDetalhamentoInstrutor toDetailDto(Instrutor instrutor) {
        return new DadosDetalhamentoInstrutor(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                enderecoMapper.toDto(instrutor.getEndereco()),
                instrutor.isAtivo()
        );
    }

    public DadosListagemInstrutor toListDto(Instrutor instrutor) {
        return new DadosListagemInstrutor(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getEspecialidade()
        );
    }
}