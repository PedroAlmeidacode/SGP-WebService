package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;
import pt.ufp.info.esof.sgp.dtos.creators.EmpregadoCreateDTO;

@Data
public class TarefaResponseDTO {
    private int duracaoEstimada;
    private String titulo;
    private String descricao;
    private EmpregadoCreateDTO empregado;
    private TarefaAtualDTO tarefaAtual;
}

