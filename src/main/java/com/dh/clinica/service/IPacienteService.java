package com.dh.clinica.service;
import com.dh.clinica.controller.dto.PacienteRequest;
import com.dh.clinica.controller.dto.PacienteResponse;
import com.dh.clinica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService<Paciente> {

    PacienteResponse salvar (PacienteRequest paciente);

    List<PacienteResponse> buscarTodos();

    PacienteResponse buscarPorId(Integer id);

    void excluir (Integer id);

    PacienteResponse atualizar (PacienteRequest paciente);

    PacienteResponse buscarPorNome (String nome) throws ResourceNotFoundException;

    PacienteResponse buscarRg (String rg) throws ResourceNotFoundException;

}
