package pt.ufp.info.esof.sgp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.sgp.dtos.*;
import pt.ufp.info.esof.sgp.dtos.conversores.*;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.enums.Estado;
import pt.ufp.info.esof.sgp.services.ProjetoService;

import java.util.Optional;


@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();
    private final ConverterProjetoParaEstadoDescritivoDTO converterProjetoParaEDTO = new ConverterProjetoParaEstadoDescritivoDTO();
    private final ConverterEstadoParaDTO converterEstadoParaDTO = new ConverterEstadoParaDTO();
    private final ConverterCustoParaDTO converterCustoParaDTO = new ConverterCustoParaDTO();
    private final ConverterDuracaoParaDTO converterDuracaoParaDTO = new ConverterDuracaoParaDTO();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoCreateDTO projeto){
        Optional<Projeto> optionalExplicador=projetoService.createProjeto(projeto.converter());
        return optionalExplicador.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    // PATCH /projeto/tarefa/{idProjeto}
    @PatchMapping("/tarefa/{idProjeto}")
    public ResponseEntity<ProjetoResponseDTO> adicionaTarefaAProjeto(@PathVariable Long idProjeto, @RequestBody AdicionarTarefaAProjetoDTO tarefa){
        Optional<Projeto> optionalProjeto = projetoService.adicionarTarefa(idProjeto,tarefa.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }



    // GET /projeto/{idProjeto}/estadoDescritivo
    @GetMapping("/{idProjeto}/estadoDescritivo")
    public ResponseEntity<EstadoDescritivoProjetoDTO> getEstadoDescritivoProjeto(@PathVariable Long idProjeto){
        Optional<Projeto> optionalProjeto=projetoService.findById(idProjeto);
        return optionalProjeto.map(projeto -> {
            EstadoDescritivoProjetoDTO responseDTO =converterProjetoParaEDTO.converter(projeto);
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    //GET /projeto/{idProjeto}/estado
    @GetMapping("/{idProjeto}/estado")
    public ResponseEntity<EstadoResponseDTO> getEstadoProjeto(@PathVariable Long idProjeto){
        Optional<Estado> optionalEstado = projetoService.getEstadoProjeto(idProjeto);
        return optionalEstado.map(estado -> {
            EstadoResponseDTO responseDTO = converterEstadoParaDTO.converter(estado);
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    //GET /projeto/{idProjeto}/valor
    @GetMapping("/{idProjeto}/valor")
    public ResponseEntity<CustoResponseDTO> getCustoProjeto(@PathVariable Long idProjeto){
        Optional<Double> optionalCusto = projetoService.getCustoProjeto(idProjeto);
        return optionalCusto.map(custo -> {
            CustoResponseDTO responseDTO = converterCustoParaDTO.converter(custo);
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //GET /projeto/{idProjeto}/duracao
    @GetMapping("/{idProjeto}/duracao")
    public ResponseEntity<DuracaoResponseDTO> getDuracaoProjeto(@PathVariable Long idProjeto){
        Optional<Integer> optionalDuracao = projetoService.getDuracaoProjeto(idProjeto);
        return optionalDuracao.map(duracao -> {
            DuracaoResponseDTO responseDTO = converterDuracaoParaDTO.converter(duracao);
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
