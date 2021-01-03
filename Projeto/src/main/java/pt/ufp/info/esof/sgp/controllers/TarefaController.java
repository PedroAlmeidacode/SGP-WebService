package pt.ufp.info.esof.sgp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.sgp.dtos.conversores.ConverterTarefaParaDTO;
import pt.ufp.info.esof.sgp.dtos.creators.TarefaCreateDTO;
import pt.ufp.info.esof.sgp.dtos.responses.TarefaResponseDTO;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.services.TarefaService;

import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> createTarefa(@RequestBody TarefaCreateDTO tarefa) {
        Optional<Tarefa> optionalTarefa = tarefaService.createTarefa(tarefa.converter());
        return optionalTarefa.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //PATCH /tarefa/empregado/{idTarefa}
    @PatchMapping("/{idTarefa}/empregado/{idEmpregado}")
    public ResponseEntity<TarefaResponseDTO> adicionaEmpregadoATarefa(@PathVariable Long idTarefa, @PathVariable Long idEmpregado) {
        Optional<Tarefa> optionalTarefa = tarefaService.adicionarEmpregado(idTarefa, idEmpregado);
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PatchMapping("/{idTarefa}/percentual/{percentualConclusao}")
    public ResponseEntity<TarefaResponseDTO> adicionaPercentualTarefa(@PathVariable Long idTarefa, @PathVariable float percentualConclusao) {
        Optional<Tarefa> optionalTarefa = tarefaService.adicionarPercentualTarefa(idTarefa, percentualConclusao);
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{idTarefa}/tempoDedicado/{tempoDedicado}")//TODO adicionar path com tempo dedicado
    public ResponseEntity<TarefaResponseDTO> adicionaTempoDedicadoTarefa(@PathVariable Long idTarefa, @PathVariable int tempoDedicado) {
        Optional<Tarefa> optionalTarefa = tarefaService.adicionarTempoDedicadoTarefa(idTarefa, tempoDedicado);
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}