package pt.ufp.info.esof.sgp.models;

import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.util.ArrayList;
import java.util.List;

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
    private int calcularDuracao()
    {
        int duracao = 0;
        // soma de duracao de todas as tarefas
        for (Tarefa tarefa :this.tarefas) {
            // soma a duracao de todas as tarefas incluidas neste projeto
            duracao += tarefa.getDuracao();
        }
        return duracao;
    }


    /**
     * @return a soma do custo de todas tarefas incluidas neste projeto
     */
    private double calcularCusto()
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
     * @return objeto estado , define estado do projeto como adiantado
     * atrasado ou normal (enum estado)
     */
    private Estado calcularEstado()
    {
        // TODO calcular estado
        return null;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
}
