package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Projeto {

    private String nome;
    private Estado estadoProjeto;
    private Cliente cliente;
    private GestorDeProjeto gestorDeProjeto;
    private List<Tarefa> tarefas = new ArrayList<>();


    /**
     *
     * @return a soma da duracao de todas tarefas incluidas neste projeto
     */
    protected int calcularDuracao()
    {
        int duracao = 0;
        // soma de duracao de todas as tarefas
        for (Tarefa tarefa :this.tarefas) {
            // soma a duracao de todas as tarefas incluidas neste projeto
            duracao += tarefa.getDuracaoEstimada();
        }
        return duracao;
    }


    /**
     * @return a soma do custo de todas tarefas incluidas neste projeto
     */
    protected double calcularCusto()
    {
        double custo = 0;
        // soma do custo de todas as tarefas
        for (Tarefa tarefa:this.tarefas) {
            // soma o custo de todas as tarefas incluidas neste projeto
            custo += tarefa.getCustoTarefa();
        }
        return custo;
    }


    /**
     * Atrasada = 1
     * Normal = 2
     * Adiantada = 3
     * Concluida = 4
     * @return objeto estado , define estado do projeto
     */
    private Estado calcularEstado()
    {
        int estadoPercentual = 0;
        int numTarefas = this.tarefas.size();
        for (Tarefa tarefa:this.tarefas) {
            if(tarefa.getEstadoTarefa().equals(Estado.CONCLUIDA)) estadoPercentual += 4;
            if(tarefa.getEstadoTarefa().equals(Estado.ADIANTADA)) estadoPercentual += 3;
            if(tarefa.getEstadoTarefa().equals(Estado.NORMAL)) estadoPercentual += 2;
            if(tarefa.getEstadoTarefa().equals(Estado.ATRASADA)) estadoPercentual += 1;
        }


        // TODO determinar o estado do projeto de acordo com estadoPercentual/numTarefas

        return null;
    }

}
