package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Cargo;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.time.LocalDateTime;

// TODO atualizar UML
// TODO atualizar SRS

@Getter
@Setter
public class Tarefa {

    private int duracaoEstimada; // minutos estimados, tem que ser expressa em minutos
    // inicializada ao atribuir um empregado a tarefa
    private LocalDateTime dataIniciacao;
    private String titulo;
    private String descricao;
    private Empregado empregado;
    private TarefaAtual tarefaAtual;


    /**
     * custo de tarefa dependente da duracao estipulada e do custo por hora do
     * cargo do empregado atrbuido nela
     *
     * @return custo de tarefa
     */
    public double getCustoTarefa() {

        switch (this.empregado.getCargo()){
            // analista junior ganha 20 euros a hora = 20/60 = 0.3(3)euros ao minuto
            case ANALISTA_JUNIOR:  return this.duracaoEstimada * (20.00 / 60);
            // desenvolvedor junior ganha 10 euros a hora = 10/60 = 0.16666667 euros ao minuto
            case DES_JUNIOR: return this.duracaoEstimada * (10.00 / 60);
            // desenvolvedor senior ganha 40 euros a hora = 40/60 = 0.66666667 euros ao minuto
            case DES_SENIOR: return this.duracaoEstimada * (40.00 / 60);
            // analista senior ganha 80 euros a hora = 80/60 = 1.3(3)euros ao minuto
            case ANLISTA_SENIOR: return this.duracaoEstimada * (80.00 / 60);
            // caso nao haja cargo atribuido a este empregado
            default: return 0;
        }
    }


    /**
     * atribui tambem a data atual a tarefa como data de inciacao
     * atribui esta tarefa na lista de tarefas de empregado
     * @param empregado atrbuido a esta tarefa
     */
    public void atribuirEmpregadoaTarefa(Empregado empregado) {
        this.empregado = empregado;
        // inciacada a data de inciciacao da tarefa
        this.dataIniciacao = LocalDateTime.now();
        // criada a tarefa atual
        this.tarefaAtual = new TarefaAtual(this,LocalDateTime.now());
        // adiciona esta tarefa as tarefas de empregado
        empregado.getTarefas().add(this);
    }


    /**
     *
     * @return estado de tarefa
     */
    public Estado getEstadoTarefa(){
        int tempoDedicado = this.tarefaAtual.getTempoDedicado();
        // da nos um valor de 0-100, da percenatgem de tempo estimado dado, usada
        int percentagemTempoUsada = tempoDedicado * 100 / this.duracaoEstimada;
        // percentual da tarefa feita pelo empregado, atribuida pelo gestor
        float percentualConclusao = this.tarefaAtual.getPercentualConclusao();


        // TODO incluir as datas de inciacao e as datas de atualizao nos calculos
        // se ja tiver feito a tarefa toda
        if(percentualConclusao == 100) return Estado.CONCLUIDA;
        // percentagem de tempo usada em relacao a percentagem feita
        // se a perc de tempo usada < perc feita
        if(percentagemTempoUsada < percentualConclusao) return Estado.ADIANTADA;
        // perc tempo usada > = ao feito por diferenca de menos 10
        if(percentagemTempoUsada >= percentualConclusao && (percentagemTempoUsada-percentualConclusao) < 10) return Estado.NORMAL;
        else return Estado.ATRASADA;
    }



}
