package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.UsuarioRequest;
import com.dh.clinica.controller.dto.UsuarioResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.UsuarioServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

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
        if (usuarioService.buscarPorId(id) != null ){
            usuarioService.excluir(id);
            log.debug("Usuario deletado com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
        }else {
            throw new ResourceNotFoundException("Não foi possivel deletar usuario pelo ID");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Buscando usuario por ID");
        UsuarioResponse usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            log.debug("Usuario encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuario);
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar usuario por ID");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity <List<UsuarioResponse>> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        log.debug("Realizando buscar por nome !");
        List<UsuarioResponse> usuario = usuarioService.buscarPorNome(nome);
        if (usuario != null) {
            log.debug("nome do usuario foi encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuario);
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar nome do usuario");
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@RequestBody UsuarioRequest usuario) throws ResourceNotFoundException {
        log.debug("Realizando cadastro de usuarios");
        if (Objects.nonNull(usuario.getEmail())) {
            log.debug("Usuario salvo com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.salvar(usuario));
        } else {
            throw new ResourceNotFoundException("Não foi possivel salvar paciente");
        }
    }

    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizar (@RequestBody UsuarioRequest usuario) throws ResourceNotFoundException {
        log.debug("Atualizando usuarios");
        if (usuario.getEmail() != null && usuarioService.buscarEmail(usuario.getEmail()) != null) {
            log.debug("Usuario atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.atualizar(usuario));
        }else {
            throw new ResourceNotFoundException("Não foi possivel atualizar todos os pacientes");
        }
    }


}
