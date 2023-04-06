package com.dh.clinica.service.impl;
import com.dh.clinica.controller.dto.DentistaRequest;
import com.dh.clinica.controller.dto.DentistaResponse;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.repository.IDentistaRepository;
import com.dh.clinica.service.IDentistaService;
import com.dh.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DentistaServiceImpl implements IDentistaService<Dentista> {


    private IDentistaRepository dentistaRepository;

    @Autowired
    public DentistaServiceImpl(IDentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public DentistaResponse salvar(DentistaRequest dentistaRequest) {
        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = mapper.convertValue(dentistaRequest, Dentista.class);
        Dentista dentistaSave = dentistaRepository.save(dentista);
        DentistaResponse dentistaResponse = mapper.convertValue(dentistaSave, DentistaResponse.class);
        return dentistaResponse;
    }


    @Override
    public List<DentistaResponse> buscarTodos() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaResponse> dentistaResponses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Dentista dentista: dentistas) {
            dentistaResponses.add(mapper.convertValue(dentista, DentistaResponse.class));
        }
        return dentistaResponses;
    }

    @Override
    public Optional<Dentista> buscarPorId(Integer id) {
        return dentistaRepository.findById(id);
    }

    @Override
    public void excluir(Integer id) {
        dentistaRepository.deleteById(id);
    }

    @Override
    public Dentista atualizar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    @Override
    public Optional<Dentista> buscarPorNome(String nome) {
        return dentistaRepository.findDentistaBynomeContainingIgnoreCase(nome);
    }


}
