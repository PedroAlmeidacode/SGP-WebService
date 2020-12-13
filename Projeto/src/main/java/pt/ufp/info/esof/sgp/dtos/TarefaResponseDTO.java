package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TarefaResponseDTO {
    private int duracaoEstimada;
    private String titulo;
    private String descricao;
    private EmpregadoCreateDTO empregado;
    private TarefaAtualDTO tarefaAtual;
}

