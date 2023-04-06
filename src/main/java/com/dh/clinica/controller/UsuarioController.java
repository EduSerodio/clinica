package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.UsuarioResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.UsuarioServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// ANOTAÇÕES REALIZADAS PARA INDICAR QUE ESSA CLASSE É UMA CONTROLLER E QUE ESTAMOS MAPEANDO ELA
// COM O PARÂMETRO ("/usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    final static Logger log = Logger.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioServiceImpl usuarioService;     // serve para injetar as dependencias da classe "usuarioService"

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() throws ResourceNotFoundException {
        log.debug("Armazenado lista de usuarios em variavel");
        List<UsuarioResponse> usuarios = usuarioService.buscarTodos();
        if (!usuarios.isEmpty() ) {
            log.debug("Lista de usuarios encontrada com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarios);
        }else {
            throw new ResourceNotFoundException("Não foi possivel listar todos os usuarios");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Deletando usuario pelo ID");
        if (usuarioService.buscarPorId(id).isPresent()){
            usuarioService.excluir(id);
            log.debug("Usuario deletado com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
        }else {
            throw new ResourceNotFoundException("Não foi possivel deletar usuario pelo ID");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Buscanddo usuario por ID");
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            log.debug("Usuario encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuario);
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar usuario por ID");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Usuario> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        log.debug("Realizando buscar por nome !");
        Usuario usuario = usuarioService.buscarPorNome(nome).orElse(null);
        if (usuario != null) {
            log.debug("nome do usuario foi encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuario);
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar nome do usuario");
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) throws ResourceNotFoundException {
        log.debug("Realizando cadastro de usuarios");
        if (Objects.nonNull(usuario.getNivelAcesso())) {
            log.debug("Usuario salvo com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.salvar(usuario));
        } else {
            throw new ResourceNotFoundException("Não foi possivel salvar paciente");
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario) throws ResourceNotFoundException {
        log.debug("Atualizando usuarios");
        if (usuario.getId() != null && usuarioService.buscarPorId(usuario.getId()).isPresent()) {
            log.debug("Usuario atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.atualizar(usuario));
        }else {
            throw new ResourceNotFoundException("Não foi possivel atualizar todos os pacientes");
        }
    }


}
