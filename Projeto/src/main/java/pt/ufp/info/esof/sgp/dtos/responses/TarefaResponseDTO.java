package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.dtos.creators.EmpregadoCreateDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {
    private int duracaoEstimada;
    private String titulo;
    private String descricao;
    private EmpregadoCreateDTO empregado;
    private TarefaAtualDTO tarefaAtual;
}

