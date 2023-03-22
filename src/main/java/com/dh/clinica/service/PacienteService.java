package com.dh.clinica.service;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.impl.PacienteDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteDaoImpl pacienteIDao;

    public Paciente salvar (Paciente paciente){
        return pacienteIDao.salvar(paciente);
    }

    public List<Paciente> listarTodos(){
        return pacienteIDao.buscarTodos();
    }

    public Optional<Paciente> buscarPorId(Integer id) {
      return pacienteIDao.buscarPorId(id);
    }

    public Paciente atualizar (Paciente paciente){
        return pacienteIDao.atualizar(paciente);
    }

    public void excluir(Integer id){
        pacienteIDao.excluir(id);
    }

}
