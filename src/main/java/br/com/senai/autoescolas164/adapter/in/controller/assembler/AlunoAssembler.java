package br.com.senai.autoescolas164.adapter.in.controller.assembler;

import br.com.senai.autoescolas164.adapter.in.controller.InstrutorController;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class AlunoAssembler {
    private final PagedResourcesAssembler<DadosListagemInstrutor> pagedResourcesAssembler;

    public EntityModel<DadosDetalhamentoInstrutor> toCreate(
            DadosDetalhamentoInstrutor dados){
        EntityModel<DadosDetalhamentoInstrutor> model = EntityModel.of(
                dados,
                linkTo(methodOn(InstrutorController.class)
                        .listar(null))
                        .withRel("listar")
        );
        return model;
    }

    public PagedModel<EntityModel<DadosListagemInstrutor>> toList(
            Page<DadosListagemInstrutor> page){
        PagedModel<EntityModel<DadosListagemInstrutor>> pagedModel = pagedResourcesAssembler
                .toModel(
                        page,
                        dados -> EntityModel.of(
                                dados,
                                linkTo(methodOn(InstrutorController.class)
                                        .detalhar(dados.id()))
                                        .withSelfRel()
                        )
                );
        return pagedModel;

    }

    public EntityModel<DadosDetalhamentoInstrutor> toDetail(
            DadosDetalhamentoInstrutor dados){
        EntityModel<DadosDetalhamentoInstrutor> model = EntityModel.of(
                dados,
                linkTo(methodOn(InstrutorController.class)
                        .atualizar(null))
                        .withRel("atualizar")
        );
        if (dados.ativo()){
            model.add(linkTo(methodOn(InstrutorController.class)
                    .excluir(dados.id()))
                    .withRel("excluir"));
        } else {
            model.add(linkTo(methodOn(InstrutorController.class)
                    .reativar(dados.id()))
                    .withRel("reativar"));
        }
        return model;
    }
}
