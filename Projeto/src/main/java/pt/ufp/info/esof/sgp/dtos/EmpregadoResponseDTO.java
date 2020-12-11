package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmpregadoResponseDTO {

    private String email;
    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;
}
