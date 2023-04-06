package com.dh.clinica.service.impl;

import com.dh.clinica.controller.dto.PacienteResponse;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService<Paciente> {


    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<PacienteResponse> buscarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteResponse> pacienteResponses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Paciente paciente: pacientes){
            pacienteResponses.add(mapper.convertValue(paciente, PacienteResponse.class));
        }
        return pacienteResponses;
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public void excluir(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findPacienteBynomeContainingIgnoreCase(nome);
    }
}
