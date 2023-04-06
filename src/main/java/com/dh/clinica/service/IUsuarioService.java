package com.dh.clinica.service;

import com.dh.clinica.controller.dto.UsuarioResponse;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService<Usuario> {

    Usuario salvar (Usuario usuario);

    List<UsuarioResponse> buscarTodos();

    Optional<Usuario> buscarPorId(Integer id);

    void excluir (Integer id);

    Usuario atualizar (Usuario usuario);

    Optional<Usuario> buscarPorNome (String nome);

}
