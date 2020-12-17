package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Projeto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    private Cliente cliente;

    @OneToMany (mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();


    /**
     * @return a soma da duracao de todas tarefas incluidas neste projeto
     */
    public int calcularDuracao() {
        // retorna a soma de duracao de todas as tarefas
        return this.tarefas.stream().mapToInt(Tarefa::getDuracaoEstimada).sum();
    }


    /**
     * @return a soma do custo de todas tarefas incluidas neste projeto
     */
    public double calcularCusto() {
        // retorna o custo da soma de todas as tarefas
        return this.tarefas.stream().mapToDouble(Tarefa::getCustoTarefa).sum();
    }


    /**
     * Atrasada = 1
     * Normal = 2
     * Adiantada = 3
     * Concluida = 4
     *
     * @return objeto estado , define estado do projeto
     */
    public Estado calcularEstado() {
        // se nao houver nenhuma tarefa neste projeto
        if (this.tarefas.isEmpty()) return Estado.SEM_TAREFAS_SUBMETIDAS;
        int estadoPercentual = 0;
        int numTarefas = this.tarefas.size();
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getEstadoTarefa().equals(Estado.CONCLUIDA)) estadoPercentual += 4;
            if (tarefa.getEstadoTarefa().equals(Estado.ADIANTADA)) estadoPercentual += 3;
            if (tarefa.getEstadoTarefa().equals(Estado.NORMAL) || tarefa.getEstadoTarefa().equals(Estado.TAREFA_NAO_ATRBUIDA))
                estadoPercentual += 2;
            if (tarefa.getEstadoTarefa().equals(Estado.ATRASADA)) estadoPercentual += 1;
        }
        long mediaEstadoLong = estadoPercentual / (long) numTarefas;
        // arredonda o estado de todos os proejtos para um estado mais geral
        int mediaEstado = Math.round(mediaEstadoLong);
        // estado do projeto
        switch (mediaEstado) {
            case 1:
                return Estado.ATRASADA;
            case 3:
                return Estado.ADIANTADA;
            case 4:
                return Estado.CONCLUIDA;
            // caso resulte em outro valor atribui se como
            // caso seja 2 ->
            default:
                return Estado.NORMAL;
        }
    }

    public void adicionarTarefa(Tarefa tarefa) {
        if(!this.tarefas.contains(tarefa)){
            this.tarefas.add(tarefa);
            tarefa.setProjeto(this);

        }
    }

}
