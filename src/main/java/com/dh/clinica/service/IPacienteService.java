package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;


public interface IPacienteService<Paciente> {

    Paciente salvar (Paciente paciente);

    List<Paciente> buscarTodos();

    Optional<Paciente> buscarPorId(Integer id);

    void excluir (Integer id);

    Paciente atualizar (Paciente paciente);

    Optional<Paciente> buscarPorNome (String nome);

}
