package com.dh.clinica.service;

import com.dh.clinica.controller.dto.DentistaRequest;
import com.dh.clinica.controller.dto.DentistaResponse;

import java.util.List;
import java.util.Optional;


public interface IDentistaService<Dentista> {

    DentistaResponse salvar (DentistaRequest dentista);

    List<DentistaResponse> buscarTodos();

    Optional<Dentista> buscarPorId(Integer id);

    void excluir (Integer id);

    Dentista atualizar (Dentista dentista);

    Optional<Dentista> buscarPorNome (String nome);

}
