package pt.ufp.info.esof.sgp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.sgp.dtos.DTOStaticFactory;
import pt.ufp.info.esof.sgp.dtos.creators.ProjetoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.responses.*;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.enums.Estado;
import pt.ufp.info.esof.sgp.services.ProjetoService;

import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoCreateDTO projeto) {
        Optional<Projeto> optionalExplicador = projetoService.createProjeto(projeto.converter());
        return optionalExplicador.map(value -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    // PATCH /projeto/tarefa/{idProjeto}
    @PatchMapping("/{idProjeto}/tarefa/{idTarefa}")
    public ResponseEntity<ProjetoResponseDTO> adicionaTarefaAProjeto(@PathVariable Long idProjeto, @PathVariable Long idTarefa) {
        Optional<Projeto> optionalProjeto = projetoService.adicionarTarefa(idProjeto, idTarefa);
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.projetoResponseDTO(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    // GET /projeto/{idProjeto}/estadoDescritivo
    @GetMapping("/{idProjeto}/estadoDescritivo")
    public ResponseEntity<EstadoDescritivoProjetoDTO> getEstadoDescritivoProjeto(@PathVariable Long idProjeto) {
        Optional<Projeto> optionalProjeto = projetoService.findById(idProjeto);
        return optionalProjeto.map(projeto -> ResponseEntity.ok(dtoStaticFactory.estadoDescritivoProjetoDTO(projeto))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //GET /projeto/{idProjeto}/estado
    @GetMapping("/{idProjeto}/estado")
    public ResponseEntity<EstadoResponseDTO> getEstadoProjeto(@PathVariable Long idProjeto) {
        Optional<Estado> optionalEstado = projetoService.getEstadoProjeto(idProjeto);
        return optionalEstado.map(estado ->ResponseEntity.ok(dtoStaticFactory.estadoResponseDTO(estado))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //GET /projeto/{idProjeto}/valor
    @GetMapping("/{idProjeto}/custo")
    public ResponseEntity<CustoResponseDTO> getCustoProjeto(@PathVariable Long idProjeto) {
        Optional<Double> optionalCusto = projetoService.getCustoProjeto(idProjeto);
        return optionalCusto.map(custo -> ResponseEntity.ok(dtoStaticFactory.custoResponseDTO(custo))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //GET /projeto/{idProjeto}/duracao
    @GetMapping("/{idProjeto}/duracao")
    public ResponseEntity<DuracaoResponseDTO> getDuracaoProjeto(@PathVariable Long idProjeto) {
        Optional<Integer> optionalDuracao = projetoService.getDuracaoProjeto(idProjeto);
        return optionalDuracao.map(duracao -> ResponseEntity.ok(dtoStaticFactory.duracaoResponseDTO(duracao))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}