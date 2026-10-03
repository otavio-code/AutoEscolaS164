package br.com.senai.autoescolas164.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.parser.Entity;


public interface StdFeaturePort<C, R, U, D, DET, URI, ID, PG> {
    ResponseEntity<EntityModel<DET>> cadastrar(C dados, URI uriBuilder);
    ResponseEntity<PagedModel<Page<R>>> listar(PG paginacao);
    ResponseEntity<EntityModel<DET>> detalhar(ID id);
    ResponseEntity<DET> atualizar(U dados);
    ResponseEntity<D> excluir(ID id);
    ResponseEntity<DET> reativar(ID id);
}
