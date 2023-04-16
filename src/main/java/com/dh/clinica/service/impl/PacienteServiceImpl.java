package com.dh.clinica.service.impl;

import com.dh.clinica.controller.dto.PacienteRequest;
import com.dh.clinica.controller.dto.PacienteResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.IDentistaRepository;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService<Paciente> {


    private ObjectMapper mapper = new ObjectMapper();

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteResponse salvar(PacienteRequest pacienteRequest) {
        Paciente paciente = mapper.convertValue(pacienteRequest, Paciente.class);
        Paciente save = pacienteRepository.save(paciente);
        PacienteResponse pacienteResponse = mapper.convertValue(save, PacienteResponse.class);
        return pacienteResponse;
    }

    @Override
    public List<PacienteResponse> buscarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteResponse> pacienteResponses = new ArrayList<>();
        for (Paciente paciente: pacientes){
            pacienteResponses.add(mapper.convertValue(paciente, PacienteResponse.class));
        }
        return pacienteResponses;
    }

    @Override
    public PacienteResponse buscarPorId(Integer id) {
        Paciente pacienteId = pacienteRepository.findById(id).orElse(null);
        PacienteResponse pacienteResponse = mapper.convertValue(pacienteId, PacienteResponse.class);
        return pacienteResponse;

    }

    @Override
    public void excluir(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteResponse atualizar(PacienteRequest paciente) {
        Paciente paciente1 = mapper.convertValue(paciente, Paciente.class);
        Paciente save = pacienteRepository.saveAndFlush(paciente1);
        PacienteResponse pacienteResponse = mapper.convertValue(save, PacienteResponse.class);
        return pacienteResponse;
    }

    @Override
    public PacienteResponse buscarPorNome(String nome) throws ResourceNotFoundException {
        Paciente pacienteBynome = pacienteRepository.findPacienteBynomeContainingIgnoreCase(nome);
        if (pacienteBynome != null) {
            PacienteResponse pacienteResponse = mapper.convertValue(pacienteBynome, PacienteResponse.class);
            return pacienteResponse;
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar o paciente pelo nome");
        }
    }

    @Override
    public PacienteResponse buscarRg(String rg) throws ResourceNotFoundException {
        Paciente pacienteByRg = pacienteRepository.findPacienteByRgContainingIgnoreCase(rg);
        if (pacienteByRg != null) {
            PacienteResponse pacienteResponse = mapper.convertValue(pacienteByRg, PacienteResponse.class);
            return pacienteResponse;
        } else {
            throw new ResourceNotFoundException("Não foi possivel encotrar p rg do paciente");
        }

    }
}
