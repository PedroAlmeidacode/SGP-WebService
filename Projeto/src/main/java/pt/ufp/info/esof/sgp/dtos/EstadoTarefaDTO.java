package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.time.LocalDateTime;

@Data
public class EstadoTarefaDTO {
    private String nomeEmpregado;
    private int duracaoEstimada; // minutos estimados, tem que ser expressa em minutos
    private LocalDateTime dataIniciacao;
    private String titulo;
    private float percentualConclusao; // de 0-100, definido por gestor de projeto
    private Estado estadoTarefa;
}
