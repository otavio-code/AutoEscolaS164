package br.com.senai.autoescolas164.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface StdFeaturePort<C, R, U, D, DET, URI, ID, PG> {
    ResponseEntity<DET> cadastrar(C dados, URI uriBuilder);
    ResponseEntity<Page<R>> listar(PG paginacao);
    ResponseEntity<DET> detalhar(ID id);
    ResponseEntity<DET> atualizar(U dados);
    ResponseEntity<D> excluir(ID id);

}
