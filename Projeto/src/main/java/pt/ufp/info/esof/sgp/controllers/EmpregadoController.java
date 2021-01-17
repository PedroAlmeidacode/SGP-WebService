package pt.ufp.info.esof.sgp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.sgp.dtos.DTOStaticFactory;
import pt.ufp.info.esof.sgp.dtos.creators.EmpregadoCreateDTO;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.services.EmpregadoService;

import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {

    private final EmpregadoService empregadoService;
    private final DTOStaticFactory dtoStaticFactory = DTOStaticFactory.getInstance();

    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @PostMapping
    public ResponseEntity<EmpregadoCreateDTO> createEmpregado(@RequestBody EmpregadoCreateDTO empregado) {
        Optional<Empregado> optionalEmpregado = empregadoService.createEmpregado(empregado.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(dtoStaticFactory.empregadoCreateDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
