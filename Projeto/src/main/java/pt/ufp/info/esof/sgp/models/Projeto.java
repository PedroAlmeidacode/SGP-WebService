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


    private int calcularDuracao()
    {
        return 0;
    }

    private double calcularCusto()
    {
        return 0;
    }

    private Estado calcularEstado()
    {
        return null;
    }
}
