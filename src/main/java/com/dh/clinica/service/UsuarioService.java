package com.dh.clinica.service;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.UsuarioDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    //IMPLEMENTANDO "UsuarioDaoImpl" para receber os métodos criados na interface genérica
    @Autowired
    private UsuarioDaoImpl usuarioIDao;

    public Usuario salvar (Usuario usuario){
        return usuarioIDao.salvar(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioIDao.buscarTodos();
    }

    public Optional<Usuario> buscarPorId (Integer id) {
        return usuarioIDao.buscarPorId(id);
    }

    public Usuario atualizar (Usuario usuario){
        return usuarioIDao.atualizar(usuario);
    }

    public void excluir(Integer id){
        usuarioIDao.excluir(id);
    }
}
