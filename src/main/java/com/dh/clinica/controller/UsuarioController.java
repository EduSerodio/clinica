package com.dh.clinica.controller;
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
    public ResponseEntity<List<Usuario>> listarTodos() {
        log.debug("Armazenado lista de usuarios em variavel");
        List<Usuario> usuarios = usuarioService.buscarTodos();
        if (!usuarios.isEmpty() ) {
            log.debug("Lista de usuarios encontrada com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarios);
        }else {
            log.debug("Lista de usuarios nao encontrada - STATUS 404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable Integer id) {
        log.debug("Deletando usuario pelo ID");
        if (usuarioService.buscarPorId(id).isPresent()){
            usuarioService.excluir(id);
            log.debug("Usuario deletado com sucesso");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id){
        log.debug("Buscanddo usuario por ID");
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            log.debug("Usuario encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuario);
        }else {
            log.debug("Usuario nao encontrado - STATUS 404");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Usuario> buscarPorNome (@PathVariable String nome) {
        return ResponseEntity.ok(usuarioService.buscarPorNome(nome).orElse(null) );
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        log.debug("Realizando cadastro de usuarios");

        if (Objects.nonNull(usuario.getNivelAcesso())) {
            log.debug("Usuario salvo com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.salvar(usuario));
        } else {
            log.debug("Usuario nao encontrado - STATUS 404");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario) {
        log.debug("Atualizando usuarios");
        if (usuario.getId() != null && usuarioService.buscarPorId(usuario.getId()).isPresent()) {
            log.debug("Usuario atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(usuarioService.atualizar(usuario));
        }else {
            log.debug("Usuario nao foi atualizado - STATUS 404");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


}
