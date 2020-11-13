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

    // calcula a soma da duracao de todas tarefas incluidas neste projeto
    private int calcularDuracao()
    {
        int duracao = 0;
        for (Tarefa tarefa :this.tarefas) {
            // soma a duracao de todas as tarefas incluidas neste projeto
            duracao += tarefa.getTempoEstimado();
        }
        return  duracao;
    }

    // calcula a soma do custo de todas tarefas incluidas neste projeto
    private double calcularCusto()
    {
        return 0;
    }

    private Estado calcularEstado()
    {
        return null;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
}
