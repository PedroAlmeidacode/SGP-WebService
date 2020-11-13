package pt.ufp.info.esof.sgp.models;

import java.util.ArrayList;
import java.util.List;

public class GestorDeProjeto extends Utilizador{

    private List<Projeto> projetosGeridos = new ArrayList<>();

    private void atribuirPercentualDeConclusao(float percentual, Tarefa tarefa)
    {
        for (Projeto projeto:this.projetosGeridos) {
            // se as tarefas de todos os projetos geridos nao contiver a tarefa a atribuir
            if(!projeto.getTarefas().contains(tarefa)){
                // esta tarefa ta fora do scoop de tarefas geridas
                System.out.println("ERRO:");
                System.out.println(tarefa.getTitulo() + "nao é gerida pelo utilizador "+ this.getNome());
                return;
            }
        }
        // atribui o percentual de conclusão a uma determinada tarefa
        tarefa.getTarefaAtual().setPercentualConclusao(percentual);
    }

}
