package pt.ufp.info.esof.sgp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.sgp.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.conversores.ConverterProjetoParaDTO;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.services.ProjetoService;

import java.util.Optional;


@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoCreateDTO projeto){
        Optional<Projeto> optionalExplicador=projetoService.createProjeto(projeto.converter());
        return optionalExplicador.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }




}
