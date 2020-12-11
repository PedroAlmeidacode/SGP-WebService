package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.*;

// devolver este dto como response
@Data
public class TarefaCreateDTO implements CreateDTO<Tarefa>{
    private int duracaoEstimada;
    private String titulo;
    private String descricao;


    @Override
    public Tarefa converter(){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(this.getTitulo());
        tarefa.setDuracaoEstimada(this.getDuracaoEstimada());
        tarefa.setDescricao(this.getDescricao());
        return tarefa;
    }
}
