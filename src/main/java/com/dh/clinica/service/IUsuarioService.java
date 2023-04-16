package com.dh.clinica.service;

import com.dh.clinica.controller.dto.UsuarioRequest;
import com.dh.clinica.controller.dto.UsuarioResponse;

import java.util.List;


public interface IUsuarioService<Usuario> {

    UsuarioResponse salvar (UsuarioRequest usuario);

    List<UsuarioResponse> buscarTodos();

    UsuarioResponse buscarPorId(Integer id);

    void excluir (Integer id);

    UsuarioResponse atualizar (UsuarioRequest usuario);

    List<UsuarioResponse> buscarPorNome (String nome);

}
