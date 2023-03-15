package com.dh.clinica.service;

import java.util.List;

public interface IService <T> {

    T salvar(T t);

    List<T> buscarTodos();

    void excluir(Integer id);

}
