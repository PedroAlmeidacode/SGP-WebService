package pt.ufp.info.esof.sgp.models;

import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.util.ArrayList;
import java.util.List;

public class Empregado extends Utilizador {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;

    // TODO apenas pode ser adicionado por empregados ?? ficar aqui ou em Tarefa ??

    /**
     * @param tempo dedicado pelo empregado para esta tarefa
     * @param tarefa em execucao pelo empregado
     */
    private void incluirTempoDedicado(int tempo, Tarefa tarefa)
    {
        // se o empregado que esta a trabalhar na tarefa nao for o que esta a tentar
        // incluir o tempo dedicado - ERRO
        if(!tarefa.getEmpregado().equals(this)){
            System.out.println("ERRO:");
            System.out.println(tarefa.getTitulo() + "nao é do empregado: "+ this.getNome());
            return;
        }
        // ir à tarefa buscar a tarefa atual e inserir tempo dedicado --> setTempodedicado
        tarefa.getTarefaAtual().setTempoDedicado(tempo);
    }

    public Cargo getCargo() {
        return cargo;
    }
}
