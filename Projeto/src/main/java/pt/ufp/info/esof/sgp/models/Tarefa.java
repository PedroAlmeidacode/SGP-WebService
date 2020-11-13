package pt.ufp.info.esof.sgp.models;

public class Tarefa {


    private int tempoEstimado; //minutos
    private double custoEstimado; //eurinhos
    private String titulo;
    private String descricao;
    private Empregado empregado;
    private TarefaAtual tarefaAtual;

    public TarefaAtual getTarefaAtual() {
        return tarefaAtual;
    }

    public String getTitulo() {
        return titulo;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }
}
