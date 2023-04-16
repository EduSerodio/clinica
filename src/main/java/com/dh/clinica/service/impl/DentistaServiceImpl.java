package com.dh.clinica.service.impl;
import com.dh.clinica.controller.dto.DentistaRequest;
import com.dh.clinica.controller.dto.DentistaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DentistaServiceImpl(IDentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public DentistaResponse salvar(DentistaRequest dentistaRequest) {
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
    public DentistaResponse buscarPorId(Integer id) throws ResourceNotFoundException {
        Dentista dentista = dentistaRepository.findDentistaById(id);
        if (dentista != null) {
            DentistaResponse dentistaResponse1 = mapper.convertValue(dentista, DentistaResponse.class);
            return dentistaResponse1;
        }else {
            throw new ResourceNotFoundException("Dentista não encontrado peço ID solicitado");
        }
    }

    @Override
    public void excluir(Integer id) {
        dentistaRepository.deleteById(id);
    }

    @Override
    public DentistaResponse atualizar(DentistaRequest dentista) {
        Dentista dentistaEntity = mapper.convertValue(dentista, Dentista.class);
        Dentista dentistaEntityAtualizado = dentistaRepository.saveAndFlush(dentistaEntity);
        DentistaResponse dentistaResponse = mapper.convertValue(dentistaEntityAtualizado, DentistaResponse.class);
        return dentistaResponse;
    }

    @Override
    public DentistaResponse buscarPorNome(String nome) throws ResourceNotFoundException {
        Dentista dentistaByNomeContainingIgnoreCase = dentistaRepository.findDentistaByNomeContainingIgnoreCase(nome);
        if (dentistaByNomeContainingIgnoreCase != null) {
            DentistaResponse dentistaResponse = mapper.convertValue(dentistaByNomeContainingIgnoreCase, DentistaResponse.class);
            return dentistaResponse;
        } else {
            throw new ResourceNotFoundException("Dentista não encontrado pelo nome solicitado");
        }
    }

    @Override
    public DentistaResponse buscarPorMatricula(String matricula) throws ResourceNotFoundException {
        Dentista dentistaByMatriculaContainingIgnoreCase = dentistaRepository.findDentistaByMatriculaContainingIgnoreCase(matricula);
        if (dentistaByMatriculaContainingIgnoreCase != null) {
            DentistaResponse dentistaResponse = mapper.convertValue(dentistaByMatriculaContainingIgnoreCase, DentistaResponse.class);
            return dentistaResponse;
        } else {
            throw new ResourceNotFoundException("Matricula do dentista nao foi encotrada conforme o solicitado");
        }
    }

    private DentistaResponse toDentistaResponse (Dentista dentistaConvertido){
        return DentistaResponse.builder()
                .nome(dentistaConvertido.getNome())
                .sobrenome(dentistaConvertido.getSobrenome())
                .build();
    }

}
