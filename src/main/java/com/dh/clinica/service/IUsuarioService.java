package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService<Usuario> {

    Usuario salvar (Usuario usuario);

    List<Usuario> buscarTodos();

    Optional<Usuario> buscarPorId(Integer id);

    void excluir (Integer id);

    Usuario atualizar (Usuario usuario);

    Optional<Usuario> buscarPorNome (String nome);

}
