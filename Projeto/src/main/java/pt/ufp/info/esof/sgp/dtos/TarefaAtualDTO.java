package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaAtualDTO {
    private int tempoDedicado;
    private float percentualConclusao;
    private LocalDateTime ultimaAtualizacao;

}
