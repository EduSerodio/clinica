package com.dh.clinica.service;

import com.dh.clinica.model.Consulta;

import java.util.List;
import java.util.Optional;

public class ConsultaService {

    private IDao<Consulta> consultaIDao;

    public ConsultaService(IDao<Consulta> consultaIDao) {
        this.consultaIDao = consultaIDao;
    }

    public Consulta salvar (Consulta consulta){
        return consultaIDao.salvar(consulta);

    }

    public List<Consulta> buscarTodos(){
        return consultaIDao.buscarTodos();
    }

    public Optional<Consulta> buscarPorId(Integer id) {
        return consultaIDao.buscarPorId(id);
    }

    public Consulta atualizar (Consulta consulta) {
        return consultaIDao.atualizar(consulta);
    }

    public void excluir (Integer id){
        consultaIDao.excluir(id);
    }



}
