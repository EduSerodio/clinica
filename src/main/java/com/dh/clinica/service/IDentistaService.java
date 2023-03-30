package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;


public interface IDentistaService<Dentista> {

    Dentista salvar (Dentista dentista);

    List<Dentista> buscarTodos();

    Optional<Dentista> buscarPorId(Integer id);

    void excluir (Integer id);

    Dentista atualizar (Dentista dentista);

    Optional<Dentista> buscarPorNome (String nome);

}
