package com.dh.clinica.service.impl;
import com.dh.clinica.controller.dto.UsuarioResponse;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.repository.IUsuarioRepository;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService<Usuario> {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Usuario usuario: usuarios) {
          usuarioResponses.add(mapper.convertValue(usuario, UsuarioResponse.class));
        }
        return usuarioResponses;
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findUsuarioBynomeContainingIgnoreCase(nome);
    }
}
