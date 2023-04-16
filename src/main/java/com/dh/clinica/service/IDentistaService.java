package com.dh.clinica.service;
import com.dh.clinica.controller.dto.DentistaRequest;
import com.dh.clinica.controller.dto.DentistaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import java.util.List;

public interface IDentistaService<Dentista> {

    DentistaResponse salvar (DentistaRequest dentista);

    List<DentistaResponse> buscarTodos();

   DentistaResponse buscarPorId(Integer id) throws ResourceNotFoundException;

    void excluir (Integer id);

    DentistaResponse atualizar (DentistaRequest dentista);

    DentistaResponse buscarPorNome (String nome) throws ResourceNotFoundException;

    DentistaResponse buscarPorMatricula (String matricula) throws ResourceNotFoundException;

}
