package pt.ufp.info.esof.sgp.dtos.creators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.models.Tarefa;

// devolver este dto como response
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaCreateDTO implements CreateDTO<Tarefa> {
    private int duracaoEstimada;
    private String titulo;
    private String descricao;


    @Override
    public Tarefa converter() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(this.getTitulo());
        tarefa.setDuracaoEstimada(this.getDuracaoEstimada());
        tarefa.setDescricao(this.getDescricao());
        return tarefa;
    }
}
