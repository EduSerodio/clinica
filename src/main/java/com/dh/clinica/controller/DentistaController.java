package com.dh.clinica.controller;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.DentistaServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController                       //ANOTAÇÃO PARA INDICAR QUE ESSA CLASSE É UMA CONTROLLER
@RequestMapping("/dentistas")         //ANOTAÇÃO QUE INDICA O MAPEAMENTO DA ROTA PELO /dentista)
public class DentistaController {

    static final Logger log = Logger.getLogger(DentistaController.class);


    private DentistaServiceImpl dentistaService;

    @Autowired
    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public ResponseEntity<List<Dentista>> listarTodos(){
        log.debug("Listando todos os dentistas");
        List<Dentista> dentistas = dentistaService.buscarTodos();
        if (!dentistas.isEmpty()) {
            log.debug("Lista de dentistas encontrada - STATUS 200");
            return ResponseEntity.ok(dentistas);
        }else {
            log.debug("Lista de dentistas nao encontrada - STATUS 404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Dentista>> buscarPorId(@PathVariable Integer id){
        log.debug("Buscando dentista pelo ID");
        Dentista dentista = dentistaService.buscarPorId(id).orElse(null);
        if (dentista != null) {
            log.debug("dentista encontrado - STATUS 200");
            return ResponseEntity.ok(Optional.of(dentista));
        }else {
            log.debug("dentistas nao encontrada - STATUS 404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Dentista> buscarPorNome (@PathVariable String nome) {
        return ResponseEntity.ok(dentistaService.buscarPorNome(nome).orElse(null) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDentista(@PathVariable Integer id) {
        log.debug("Delentando dentista pelo ID");
        if (dentistaService.buscarPorId(id).isPresent()) {
            dentistaService.excluir(id);
            log.debug("Dentista exluido com sucesso!!");
            return ResponseEntity.status(HttpStatus.OK).body("Dentista deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping
    public ResponseEntity<Dentista> salvar(@RequestBody Dentista dentista){
        log.debug("Realizando validação da matricula do dentista");
        if (Objects.nonNull(dentista.getMatricula())) {
            log.debug("Matricula salva com sucesso! - STATUS 200");
            return ResponseEntity.ok(dentistaService.salvar(dentista));
        }else{
            log.debug("Matricula invalida - STATUS 404");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping
    public ResponseEntity<Dentista> atualizar (@RequestBody Dentista dentista){
        log.debug("Atualizando dentista");
        if (dentista.getId() != null && dentistaService.buscarPorId(dentista.getId()).isPresent()) {
            log.debug("Dentista foi atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(dentistaService.atualizar(dentista));
        }else {
            log.debug("Dentista nao foi atualizado - STATUS 404");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

    }




}
