package br.com.senai.autoescolas164.application.service;

import br.com.senai.autoescolas164.adapter.in.controller.mapper.InstrutorMapper;
import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.application.port.out.InstrutorRepository;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstrutorService {
    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    public DadosDetalhamentoInstrutor cadastrarInstrutor(DadosCadastroInstrutor dados) {
        Instrutor instrutor = mapper.toDomain(dados);
        Instrutor salvo = repository.save(instrutor);
        return mapper.toDetailDto(salvo);
    }

    @Transactional(readOnly = true)
    public @Nullable Page<DadosListagemInstrutor> listarInstrutores(Pageable paginacao) {
        return repository
                .findAllByAtivoTrue(paginacao)
                .map(mapper::toListDto);
    }

    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoInstrutor detalharInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        return mapper.toDetailDto(instrutor);
    }

    @Transactional
    public @Nullable DadosDetalhamentoInstrutor atualizarInstrutor(DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        instrutor.atualizar(
                dados.nome(),
                dados.email(),
                dados.telefone(),
                dados.especialidade(),
                enderecoMapper.toEndereco(dados.endereco())
        );
        Instrutor salvo = repository.save(instrutor);
        return mapper.toDetailDto(salvo);
    }

    @Transactional
    public void excluirInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        instrutor.excluir();
        repository.save(instrutor);
    }
}