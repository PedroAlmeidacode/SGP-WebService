package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class TarefaAtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tempoDedicado; // em minutos, colocado por empregado
    private float percentualConclusao; // de 0-100, definido por gestor de projeto
    private LocalDateTime ultimaAtualizacao; // definido em setPercentualConclusao

    /**
     * set by gestor de projeto de tarefa
     *
     * @param percentualConclusao percentagem de cocnlusao definida pelo gestor
     *                            deste projeto
     */
    public void setPercentualConclusao(float percentualConclusao) {
        this.percentualConclusao = percentualConclusao;
        // quando o gestor atualiza o percentual de conclusao a ultima atualizacao
        // passa a ser a data atual
        setUltimaAtualizacaoParaAgora();
    }

    public void setUltimaAtualizacaoParaAgora(){
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void adicionarTempoDedicado(int tempoDedicado)
    {
        this.tempoDedicado+= tempoDedicado;
    }
}
