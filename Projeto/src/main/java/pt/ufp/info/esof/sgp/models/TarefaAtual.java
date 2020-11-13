package pt.ufp.info.esof.sgp.models;

import java.time.LocalDateTime;

public class TarefaAtual {

    private Tarefa tarefa;
    private int tempoDedicado; // em minutos, colocado por empregado
    private float percentualConclusao; // de 0-100, definido por gestor de projeto
    private LocalDateTime ultimaAtualizacao; // definido em setPercentualConclusao

    // set by empregado de tarefa
    public void setTempoDedicado(int tempoDedicado) {
        this.tempoDedicado = tempoDedicado;
    }

    /**
     * set by gestor de projeto de tarefa
     * @param percentualConclusao percentagem de cocnlusao definida pelo gestor
     *                            deste projeto
     */
    public void setPercentualConclusao(float percentualConclusao) {
        this.percentualConclusao = percentualConclusao;
        // quando o gestor atualiza o percentual de conclusao a ultima atualizacao
        // passa a ser a data atual
        this.ultimaAtualizacao = LocalDateTime.now();
    }
}
