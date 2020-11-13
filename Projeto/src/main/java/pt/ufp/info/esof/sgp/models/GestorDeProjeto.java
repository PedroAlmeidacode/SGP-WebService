package pt.ufp.info.esof.sgp.models;

import java.util.ArrayList;
import java.util.List;

public class GestorDeProjeto extends Utilizador{

    private List<Projeto> projetosGeridos = new ArrayList<>();

    // TODO apenas pode ser adicionado por Gestor ?? ficar aqui ou em Tarefa ??
    /**
     * atribui a tarefa um pecentual que define o progesso de execucao da mesma
     * @param percentual de conclusao desta tarefa, definido pelo gestor
     * @param tarefa gerida por este utilizador
     */
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
