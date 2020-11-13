package pt.ufp.info.esof.sgp.models;

import java.time.LocalDateTime;

public class TarefaAtual {

    private Tarefa tarefa;
    private int tempoDedicado; //minutos ---> empregado irá por isto
    private float percentualConclusao; // % é o gestor do projeto que irá dar este percentual
    private LocalDateTime ultimaAtualizacao;


    // set by empregado de tarefa
    public void setTempoDedicado(int tempoDedicado) {
        this.tempoDedicado = tempoDedicado;
    }

    // set by gestor de projeto de tarefa
    public void setPercentualConclusao(float percentualConclusao) {
        this.percentualConclusao = percentualConclusao;

        // quando o gestor atualiza o percentual de conclusao a ultima atualizacao
        // passa a ser a data atual
        this.ultimaAtualizacao = LocalDateTime.now();
    }
}
