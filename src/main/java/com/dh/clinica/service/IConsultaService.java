package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;


public interface IConsultaService<Consulta> {

    Consulta salvar (Consulta consulta);

    List<Consulta> buscarTodos();

    Optional<Consulta> buscarPorId(Integer id);

    void excluir (Integer id);

    Consulta atualizar (Consulta consulta);

    Optional<Consulta> buscarPorNome (String nome);

}
