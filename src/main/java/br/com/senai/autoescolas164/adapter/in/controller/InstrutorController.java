package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.assembler.InstrutorAssembler;
import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.senai.autoescolas164.application.port.in.StdFeaturePort;
import br.com.senai.autoescolas164.application.service.InstrutorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instrutores")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class InstrutorController implements StdFeaturePort<
        DadosCadastroInstrutor,
        DadosListagemInstrutor,
        DadosAtualizacaoInstrutor,
        Void,
        DadosDetalhamentoInstrutor,
        UriComponentsBuilder,
        Long,
        Pageable
        > {
    private final InstrutorService service;
    private final InstrutorAssembler assembler;

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<DadosDetalhamentoInstrutor>> cadastrar(
            @RequestBody @Valid DadosCadastroInstrutor dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoInstrutor dto = service.cadastrarInstrutor(dados);
        URI uri = uriBuilder
                .path("/instrutores/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(uri).body(assembler.toCreate(dto));
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PagedModel<Page<DadosListagemInstrutor>>> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "nome")
            Pageable paginacao) {
        Page page = service.listarInstrutores(paginacao);
        return ResponseEntity.ok(assembler.toList(page));

    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<EntityModel<DadosDetalhamentoInstrutor>> detalhar(
            @PathVariable Long id) {
        DadosDetalhamentoInstrutor dto = service.detalharInstrutor(id);
        return ResponseEntity.ok(assembler.toDetail(dto));
    }

    @Override
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizar(
            @RequestBody @Valid DadosAtualizacaoInstrutor dados) {
        return ResponseEntity.ok(service.atualizarInstrutor(dados));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirInstrutor(id);
        return ResponseEntity.noContent().build();
    }

    @Override //Colocar na porta???
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoInstrutor> reativar(@PathVariable Long id) {
        DadosDetalhamentoInstrutor dto = service.reativarInstrutor(id);
        return ResponseEntity.ok(dto);
    }
}