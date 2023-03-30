package com.dh.clinica.service.impl;

import com.dh.clinica.model.Consulta;
import com.dh.clinica.repository.IConsultaRepository;
import com.dh.clinica.service.IConsultaService;
import com.dh.clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService<Consulta> {

    @Autowired
    private IConsultaRepository consultaRepository;

    @Override
    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public List<Consulta> buscarTodos() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<Consulta> buscarPorId(Integer id) {
        return consultaRepository.findById(id);
    }

    @Override
    public void excluir(Integer id) {
        consultaRepository.deleteById(id);
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<Consulta> buscarPorNome(String nome) {
        return consultaRepository.findConsultaByDentistaNome(nome);
    }
}
