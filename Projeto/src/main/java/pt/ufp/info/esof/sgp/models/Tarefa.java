package pt.ufp.info.esof.sgp.models;

import java.time.LocalDateTime;

// TODO atualizar UML
public class Tarefa {

    // a duracao tem que ser atualizado se passar o prazo de conlusao
    // nao pode permanecer o mesmo se o projeto estiver atrasado
    private int duracao; // minutos estimados

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

    public int getDuracao() {
        return duracao;
    }


    /**
     * custo de tarefa dependente da duracao estipulada e do custo por hora do
     * empregado atrbuido nela
     * @return custo de tarefa
     */
    public double getCustoTarefa(){
        // TODO get custo de cada tarefa , multiplicar duracao(min) por cargo.custos (ifs)
        return 0;
    }
}
