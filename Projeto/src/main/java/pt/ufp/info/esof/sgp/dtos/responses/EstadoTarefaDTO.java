package pt.ufp.info.esof.sgp.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTarefaDTO {
    private String nomeEmpregado;
    private String duracaoEstimada; // minutos estimados, tem que ser expressa em minutos
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataIniciacao;
    private String titulo;
    private float percentualConclusao; // de 0-100, definido por gestor de projeto
    private Estado estadoTarefa;
}
