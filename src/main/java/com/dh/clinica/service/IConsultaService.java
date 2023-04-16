package com.dh.clinica.service;
import com.dh.clinica.controller.dto.ConsultaRequest;
import com.dh.clinica.controller.dto.ConsultaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import java.util.List;

public interface IConsultaService<Consulta> {

    ConsultaResponse salvar (ConsultaRequest consulta);

    List<ConsultaResponse> buscarTodos();

    ConsultaResponse buscarPorId(Integer id) throws ResourceNotFoundException;

    void excluir (Integer id);

    ConsultaResponse atualizar (ConsultaRequest consulta);

    ConsultaResponse buscarPorNome (String nome) throws ResourceNotFoundException;

}
