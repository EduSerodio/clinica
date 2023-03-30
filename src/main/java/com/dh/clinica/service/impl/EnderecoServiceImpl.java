package com.dh.clinica.service.impl;

import com.dh.clinica.model.Endereco;
import com.dh.clinica.repository.IEnderecoRepository;
import com.dh.clinica.service.IEnderecoService;
import com.dh.clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements IEnderecoService<Endereco> {

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Override
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<Endereco> buscarPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public void excluir(Integer id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


}
