package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;


public interface IEnderecoService<Endereco> {

    Endereco salvar (Endereco endereco);

    List<Endereco> buscarTodos();

    Optional<Endereco> buscarPorId(Integer id);

    void excluir (Integer id);

    Endereco atualizar (Endereco endereco);



}
