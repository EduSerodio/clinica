package com.dh.clinica.service;
import com.dh.clinica.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private IDao<Dentista> dentistaIDao;

    public DentistaService(IDao<Dentista> dentistaIDao) {
        this.dentistaIDao = dentistaIDao;
    }

    public Dentista salvar(Dentista dentista){
        return dentistaIDao.salvar(dentista);
    }

    public Optional<Dentista> buscarPorId(Integer id){
        return dentistaIDao.buscarPorId(id);
    }

    public List<Dentista> listarTodos() {
        return dentistaIDao.buscarTodos();
    }

    public Dentista atualizar (Dentista dentista){
        return dentistaIDao.atualizar(dentista);
    }

    public void excluir(Integer id){
        dentistaIDao.excluir(id);
    }

}
