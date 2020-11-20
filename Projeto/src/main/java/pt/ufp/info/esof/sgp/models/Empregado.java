package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Empregado extends Utilizador {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;

    /**
     * @param tempo dedicado pelo empregado para esta tarefa
     * @param tarefa em execucao pelo empregado
     */
    protected void incluirTempoDedicado(Tarefa tarefa, int tempo)
    {
        // se o empregado que esta a trabalhar na tarefa nao for o que esta a tentar
        // incluir o tempo dedicado - ERRO
        if(!tarefa.getEmpregado().equals(this)){
            System.out.println("ERRO:");
            System.out.println(tarefa.getTitulo() + "nao é do empregado: "+ this.getNome());
            return;
        }
        // ir à tarefa buscar a tarefa atual e buscar o tempo ja colocado
        int tempoAtual = tarefa.getTarefaAtual().getTempoDedicado();
        // adicionar mais o tempo
        tarefa.getTarefaAtual().setTempoDedicado(tempoAtual + tempo);
    }
}
