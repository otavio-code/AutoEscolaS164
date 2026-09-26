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
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//@Slf4j //Anotação do lombok para logs quando não usa arquitetura hexagonal
@RequiredArgsConstructor
public class InstrutorService {
    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;
    private final EnderecoMapper enderecoMapper;

    private static final Logger log = LoggerFactory.getLogger(InstrutorService.class);

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

    @Cacheable(value = "instrutores", key = "#id")
    @Transactional(readOnly = true)
    public @Nullable DadosDetalhamentoInstrutor detalharInstrutor(Long id) {
        log.info("Consultando os dados do instrutor no banco de dados");
        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        return mapper.toDetailDto(instrutor);
    }

    @CachePut(value = "instrutores", key = "#dados.id()")
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

    @CacheEvict(value = "instrutores", key = "#id")
    @Transactional
    public void excluirInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID do instrutor informado não existe!"));
        instrutor.excluir();
        repository.save(instrutor);
    }
}