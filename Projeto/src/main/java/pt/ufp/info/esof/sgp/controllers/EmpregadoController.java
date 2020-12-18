package pt.ufp.info.esof.sgp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.sgp.dtos.creators.EmpregadoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.conversores.ConverterEmpregadoParaDTO;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.services.EmpregadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {

    private final EmpregadoService empregadoService;
    private final ConverterEmpregadoParaDTO converterEmpregadoParaDTO = new ConverterEmpregadoParaDTO();


    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<EmpregadoCreateDTO>> getAllEmpregado() {
        List<EmpregadoCreateDTO> responseDTOS = new ArrayList<>();
        empregadoService.findAll().forEach(empregado -> responseDTOS.add(converterEmpregadoParaDTO.converter(empregado)));
        return ResponseEntity.ok(responseDTOS);
    }


    @PostMapping
    public ResponseEntity<EmpregadoCreateDTO> createEmpregado(@RequestBody EmpregadoCreateDTO empregado) {
        Optional<Empregado> optionalEmpregado = empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(converterEmpregadoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
