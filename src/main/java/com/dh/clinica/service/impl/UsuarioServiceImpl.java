package com.dh.clinica.service.impl;
import com.dh.clinica.controller.dto.UsuarioRequest;
import com.dh.clinica.controller.dto.UsuarioResponse;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.repository.IUsuarioRepository;
import com.dh.clinica.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService<Usuario> {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public UsuarioResponse salvar(UsuarioRequest usuario) {
        Usuario usuario1 = mapper.convertValue(usuario, Usuario.class);
        Usuario save = usuarioRepository.save(usuario1);
        UsuarioResponse usuarioResponse = mapper.convertValue(usuario1, UsuarioResponse.class);
        return usuarioResponse;

    }

    @Override
    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario: usuarios) {
          usuarioResponses.add(mapper.convertValue(usuario, UsuarioResponse.class));
        }
        return usuarioResponses;
    }

    @Override
    public UsuarioResponse buscarPorId(Integer id) {
        Usuario byId = usuarioRepository.findById(id).orElse(null);
        UsuarioResponse usuarioResponse = mapper.convertValue(byId, UsuarioResponse.class);
        return usuarioResponse;
    }

    @Override
    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponse atualizar(UsuarioRequest usuario) {
        Usuario usuario1 = mapper.convertValue(usuario, Usuario.class);
        Usuario save = usuarioRepository.save(usuario1);
        UsuarioResponse usuarioResponse = mapper.convertValue(save, UsuarioResponse.class);
        return usuarioResponse;
    }

    @Override
    public List<UsuarioResponse> buscarPorNome(String nome) {
        List<Usuario> usuarios = usuarioRepository.findUsuarioBynomeContainingIgnoreCase(nome);
        List<UsuarioResponse> usuarioResponses= new ArrayList<>();
        for (Usuario usuario: usuarios) {
            usuarioResponses.add(mapper.convertValue(usuario, UsuarioResponse.class));
        }
        return usuarioResponses;
    }

    public UsuarioResponse buscarEmail(String email) {
        Usuario usuarioByEmail = usuarioRepository.findUsuarioByEmailContainingIgnoreCase(email);
        UsuarioResponse usuarioResponse = mapper.convertValue(usuarioByEmail, UsuarioResponse.class);
        return usuarioResponse;
    }
}
