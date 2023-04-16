package com.dh.clinica.service.impl;
import com.dh.clinica.controller.dto.ConsultaRequest;
import com.dh.clinica.controller.dto.ConsultaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Consulta;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.IConsultaRepository;
import com.dh.clinica.repository.IDentistaRepository;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ConsultaServiceImpl implements IConsultaService<Consulta> {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    private IPacienteRepository iPacienteRepository;
    @Autowired
    private IDentistaRepository iDentistaRepository;

    @Override
    public ConsultaResponse salvar(ConsultaRequest consultaRequest) {
        Paciente paciente = iPacienteRepository.findById(consultaRequest.getPacienteId()).orElse(null);
        Dentista dentista = iDentistaRepository.findById(consultaRequest.getDentistaId()).orElse(null);
        Consulta consulta = new Consulta();
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setDate(consultaRequest.getDate());
        Consulta save = consultaRepository.save(consulta);
        ConsultaResponse consultaResponse = mapper.convertValue(save, ConsultaResponse.class);
        return consultaResponse;
    }

    @Override
    public List<ConsultaResponse> buscarTodos() {
        mapper.registerModule(new JavaTimeModule());
        List<Consulta> consultas = consultaRepository.findAll();
        List<ConsultaResponse> consultaResponses = new ArrayList<>();
        for (Consulta consulta : consultas) {
            consultaResponses.add(mapper.convertValue(consulta, ConsultaResponse.class));
        }
        return consultaResponses;
    }

    @Override
    public ConsultaResponse buscarPorId(Integer id) throws ResourceNotFoundException {
        Consulta byId = consultaRepository.findConsultaByid(id);
        if (byId != null) {
            ConsultaResponse consultaResponse = mapper.convertValue(byId, ConsultaResponse.class);
            return consultaResponse;
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar consulta pelo id");
        }
    }

    @Override
    public void excluir(Integer id) {
        consultaRepository.deleteById(id);
    }

    @Override
    public ConsultaResponse atualizar(ConsultaRequest consulta) {
        Consulta consulta1 = mapper.convertValue(consulta, Consulta.class);
        Consulta save = consultaRepository.save(consulta1);
        ConsultaResponse consultaResponse = mapper.convertValue(save, ConsultaResponse.class);
        return consultaResponse;
    }

    @Override
    public ConsultaResponse buscarPorNome(String nome) throws ResourceNotFoundException {
        Consulta consultaByDentistaNome = consultaRepository.findConsultaByDentistaNomeContainingIgnoreCase(nome);
        if (consultaByDentistaNome != null) {
            ConsultaResponse consultaResponse = mapper.convertValue(consultaByDentistaNome, ConsultaResponse.class);
            return consultaResponse;
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar nome da consulta");
        }
    }
}
