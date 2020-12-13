package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaAtualDTO {
    private int tempoDedicado; // em minutos, colocado por empregado
    private float percentualConclusao; // de 0-100, definido por gestor de projeto
    private LocalDateTime ultimaAtualizacao; // definido em setPercentualConclusao

}
