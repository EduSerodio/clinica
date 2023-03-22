package com.dh.clinica.service;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private IDao<Endereco> enderecoIDao;

    public EnderecoService(IDao<Endereco> enderecoIDao) {
        this.enderecoIDao = enderecoIDao;
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoIDao.salvar(endereco);
    }

    public Optional<Endereco> buscarPorId (Integer id){
        return enderecoIDao.buscarPorId(id);
    }

    public List<Endereco> listarTodos() {
        return enderecoIDao.buscarTodos();
    }

    public Endereco atualizar (Endereco endereco){
        return enderecoIDao.atualizar(endereco);
    }

    public void excluir (int id) {
        enderecoIDao.excluir(id);
    }
}
