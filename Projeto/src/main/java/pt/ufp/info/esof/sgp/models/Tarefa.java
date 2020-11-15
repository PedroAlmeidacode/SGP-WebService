package pt.ufp.info.esof.sgp.models;

import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.time.LocalDateTime;

// TODO atualizar UML
// TODO atualizar SRS


public class Tarefa {

    private int duracao; // minutos estimados, tem que ser expressa em minutos

    // inicializada ao atribuir um empregado a tarefa
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
     * cargo do empregado atrbuido nela
     *
     * @return custo de tarefa
     */
    public double getCustoTarefa() {
        // analista junior ganha 20 euros a hora = 20/60 = 0.3(3)euros ao minuto
        if (this.empregado.getCargo().equals(Cargo.ANALISTA_JUNIOR)) return this.duracao * (20.00 / 60);
        // analista junior ganha 80 euros a hora = 80/60 = 1.3(3)euros ao minuto
        if (this.empregado.getCargo().equals(Cargo.ANLISTA_SENIOR)) return this.duracao * (80.00 / 60);
        // desenvolvedor junior ganha 10 euros a hora = 10/60 = 0.16666667 euros ao minuto
        if (this.empregado.getCargo().equals(Cargo.DES_JUNIOR)) return this.duracao * (10.00 / 60);
        // desenvolvedor senior ganha 40 euros a hora = 40/60 = 0.66666667 euros ao minuto
        if (this.empregado.getCargo().equals(Cargo.DES_SENIOR)) return this.duracao * (40.00 / 60);
        System.out.println("ERRO: nao tem cargo atribuido");
        return 0;
    }


    /**
     * atribui tambem a data atual a tarefa como data de inciacao
     * atribui esta tarefa na lista de tarefas de empregado
     * @param empregado atrbuido a esta tarefa
     */
    public void atribuirEmpregadoaTarefa(Empregado empregado) {
        this.empregado = empregado;
        // adiciona esta tarefa as tarefas de empregado
        empregado.getTarefas().add(this);
        // ao atrbuir um empregado a tarefa
        // atribui tambem a data de iniciacao da tarefa
        this.dataIniciacao = LocalDateTime.now();
    }
}
