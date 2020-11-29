package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO atualizar UML
// TODO atualizar SRS

@Entity
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int duracaoEstimada; // minutos estimados, tem que ser expressa em minutos
    // inicializada ao atribuir um empregado a tarefa
    // TODO @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataIniciacao;
    private String titulo;
    private String descricao;
    @ManyToOne
    private  Projeto projeto;
    @ManyToOne(cascade = CascadeType.ALL)
    private Empregado empregado;
    @OneToOne(cascade=CascadeType.ALL)
    private TarefaAtual tarefaAtual;


    /**
     * custo de tarefa dependente da duracao estipulada e do custo por hora do
     * cargo do empregado atrbuido nela
     *
     * @return custo de tarefa
     */
    protected double getCustoTarefa() {
        // se nao tiver empregado atribuido a tarefa
        if (this.empregado == null) return 0;
        switch (this.empregado.getCargo()) {
            // analista junior ganha 20 euros a hora = 20/60 = 0.3(3)euros ao minuto
            case ANALISTA_JUNIOR:
                return this.duracaoEstimada * (20.00 / 60);
            // desenvolvedor junior ganha 10 euros a hora = 10/60 = 0.16666667 euros ao minuto
            case DES_JUNIOR:
                return this.duracaoEstimada * (10.00 / 60);
            // desenvolvedor senior ganha 40 euros a hora = 40/60 = 0.66666667 euros ao minuto
            case DES_SENIOR:
                return this.duracaoEstimada * (40.00 / 60);
            // analista senior ganha 80 euros a hora = 80/60 = 1.3(3)euros ao minuto
            case ANLISTA_SENIOR:
                return this.duracaoEstimada * (80.00 / 60);
            // caso nao haja cargo atribuido a este empregado
            default:
                return 0;
        }
    }


    /**
     * atribui tambem a data atual a tarefa como data de inciacao
     * atribui esta tarefa na lista de tarefas de empregado
     *
     * @param empregado atrbuido a esta tarefa
     */
    public void atribuirEmpregadoaTarefa(Empregado empregado) {
        // inciacada a data de inciciacao da tarefa
        this.dataIniciacao = LocalDateTime.now();
        // criada a tarefa atual
        this.tarefaAtual = new TarefaAtual();
        // colocar a ultima atualizacao como a data presente
        this.tarefaAtual.setUltimaAtualizacao(LocalDateTime.now());
        // adiciona esta tarefa as tarefas de empregado
        // adiciona o empregado a esta tarefa
        empregado.adicionarTarefa(this);
    }


    /**
     * @return estado de tarefa
     */
    protected Estado getEstadoTarefa() {

        // tarefa nao foi atribuida a empregado, se nao tiver tarefa atual
        if (this.getTarefaAtual() == null)
            return Estado.TAREFA_NAO_ATRBUIDA;

        int tempoDedicadoAtribuidoPeloEmpregado = this.tarefaAtual.getTempoDedicado();

        // da nos um valor de 0-100, da percenatgem de tempo estimado dado, usada
        int percentagemTempoUsada = tempoDedicadoAtribuidoPeloEmpregado * 100 / this.duracaoEstimada;

        // se usar mais tempo do que o previsto esta automaticamente atrasada
        if (percentagemTempoUsada > 100)
            return Estado.ATRASADA;

        // percentual da tarefa feita pelo empregado, atribuida pelo gestor
        float percentagemConclusao = this.tarefaAtual.getPercentualConclusao();

        // data prevista de finalizar de acordo com data inicializada e a duracao estimada
        LocalDateTime PrazoFinalizacaoPrevista = this.dataIniciacao.plusMinutes(this.duracaoEstimada);

        // se ja tiver concluido a tarefa
        if (percentagemConclusao == 100)
            return Estado.CONCLUIDA;

        // se a tarefa nao esta concluida (percentagem de conclusao != 100)
        // e a data da ultima atualizacao é depois da data prevista de finalizacao
        // a tarefa esta automaticamente atrasada
        if (this.getTarefaAtual().getUltimaAtualizacao().isAfter(PrazoFinalizacaoPrevista))
            return Estado.ATRASADA;

        // percentagem de tempo usada em relacao a percentagem feita
        // se a perc de tempo usada < perc concluida
        // (fez muito em pouco tempo - adiantada )
        if (percentagemTempoUsada < percentagemConclusao)
            return Estado.ADIANTADA;

        // perc tempo usada >= ao cocnluido (por diferenca de menos de 10)
        // (fez mais ao menos o equivalente ao tempo que usou)
        if (percentagemTempoUsada >= percentagemConclusao && (percentagemTempoUsada - percentagemConclusao) <= 10)
            return Estado.NORMAL;

            // percentagem de tempo usada > ao concluido (usou muito tempo e fez pouco)
        else return Estado.ATRASADA;
    }

}
