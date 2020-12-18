package pt.ufp.info.esof.sgp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.sgp.dtos.*;
import pt.ufp.info.esof.sgp.dtos.conversores.ConverterTarefaParaDTO;
import pt.ufp.info.esof.sgp.models.Projeto;
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
    public ResponseEntity<TarefaResponseDTO> createTarefa(@RequestBody TarefaCreateDTO tarefa){
        Optional<Tarefa> optionalTarefa= tarefaService.createTarefa(tarefa.converter());
        return optionalTarefa.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //PATCH /tarefa/empregado/{idTarefa}
    @PatchMapping("/empregado/{idTarefa}")
    public ResponseEntity<TarefaResponseDTO> adicionaEmpregadoATarefa(@PathVariable Long idTarefa, @RequestBody AdicionarEmpregadoATarefaDTO empregado){
        Optional<Tarefa> optionalTarefa = tarefaService.adicionarEmpregado(idTarefa,empregado.converter());
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PatchMapping("/{idTarefa}/percentualConclusao")
    public ResponseEntity<TarefaResponseDTO> adicionaPercentualTarefa(@PathVariable Long idTarefa, @RequestBody PercentualTarefaDTO percentual)
    {
        Optional<Tarefa> optionalTarefa=tarefaService.adicionarPercentualTarefa(idTarefa,percentual.converter());
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{idTarefa}/tempoDedicado")
    public ResponseEntity<TarefaResponseDTO> adicionaTempoDedicadoTarefa(@PathVariable Long idTarefa, @RequestBody TempoDedicadoTarefaDTO tempoDedicado)
    {
        Optional<Tarefa> optionalTarefa=tarefaService.adicionarTempoDedicadoTarefa(idTarefa,tempoDedicado.converter());
        return optionalTarefa.map(tarefa -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefa))).orElseGet(()->ResponseEntity.badRequest().build());
    }

}

