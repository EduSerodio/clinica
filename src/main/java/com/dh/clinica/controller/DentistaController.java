package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.DentistaRequest;
import com.dh.clinica.controller.dto.DentistaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.DentistaServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<List<DentistaResponse>> listarTodos() throws ResourceNotFoundException {
        log.debug("Listando todos os dentistas");
        List<DentistaResponse> dentistas = dentistaService.buscarTodos();
        if (!dentistas.isEmpty()) {
            log.debug("Lista de dentistas encontrada - STATUS 200");
            return ResponseEntity.ok(dentistas);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar os dentistas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <DentistaResponse> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Buscando dentista pelo ID");
        DentistaResponse dentista = dentistaService.buscarPorId(id);
        return ResponseEntity.ok(dentista);

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<DentistaResponse> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        DentistaResponse dentista = dentistaService.buscarPorNome(nome);
        if (dentista != null){
            log.debug("Nome encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(dentista);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar o nome do dentista");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDentista(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Delentando dentista pelo ID");
        if (dentistaService.buscarPorId(id) != null) {
            dentistaService.excluir(id);
            log.debug("Dentista exluido com sucesso!!");
            return ResponseEntity.status(HttpStatus.OK).body("Dentista deletado com sucesso");
        } else {
            throw new ResourceNotFoundException("Não foi possivel encontrar ID do dentista");
        }

    }

    @PostMapping
    public ResponseEntity<DentistaResponse> salvar(@RequestBody DentistaRequest dentistaRequest) throws ResourceNotFoundException {
        log.debug("Realizando validação da matricula do dentista");
        if (Objects.nonNull(dentistaRequest.getMatricula())) {
            log.debug("Matricula salva com sucesso! - STATUS 200");
            return ResponseEntity.ok(dentistaService.salvar(dentistaRequest));
        }else{
            throw new ResourceNotFoundException("Não foi possivel salvar dentista");
        }
    }

    @PutMapping
    public ResponseEntity<DentistaResponse> atualizar (@RequestBody DentistaRequest dentista) throws ResourceNotFoundException {
        log.debug("Atualizando dentista");
        if (dentista.getMatricula() != null && dentistaService.buscarPorMatricula(dentista.getMatricula()) != null) {
            log.debug("Dentista foi atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(dentistaService.atualizar(dentista));
        }else {
            throw new ResourceNotFoundException("Não foi possivel atualizar dentistas");

        }

    }




}
