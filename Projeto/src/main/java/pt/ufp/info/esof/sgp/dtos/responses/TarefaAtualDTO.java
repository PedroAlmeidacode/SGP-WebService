package pt.ufp.info.esof.sgp.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaAtualDTO {
    private int tempoDedicado;
    private float percentualConclusao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime ultimaAtualizacao;

}
