package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Tarefa;

@Data
public class AdicionarTarefaAProjetoDTO implements CreateDTO<Tarefa> {

    private Long idTarefa;


    @Override
    public Tarefa converter() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(this.idTarefa);
        return tarefa;
    }

}
