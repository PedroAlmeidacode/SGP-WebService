package pt.ufp.info.esof.sgp.models;

import java.time.LocalDateTime;

public class Tarefa {

    // o tempo estimado tem que ser atualizado se passar o prazo de conlusao
    // nao pode permanecer o mesmo se o projeto estiver atrasado
    private int tempoEstimado; // minutos estimados



    private LocalDateTime dataIniciacao; // data de construcao desta tarefa
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

}
