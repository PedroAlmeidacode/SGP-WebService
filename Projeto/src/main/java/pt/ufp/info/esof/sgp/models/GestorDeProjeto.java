package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class GestorDeProjeto extends Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany (mappedBy = "gestorDeProjeto",cascade = CascadeType.ALL)
    private List<Projeto> projetosGeridos = new ArrayList<>();

    /**
     * atribui a tarefa um pecentual que define o progesso de execucao da mesma
     *
     * @param percentual de conclusao desta tarefa, definido pelo gestor
     * @param tarefa     gerida por este utilizador
     */
    protected void atribuirPercentualDeConclusao(Tarefa tarefa, float percentual) {
        // se as tarefas de todos os projetos geridos contiver a tarefa a atribuir
        // atribui o percentual de conclusão a uma determinada tarefa
        this.projetosGeridos.stream().filter(projeto -> projeto.getTarefas().contains(tarefa))
                .forEach(projeto -> tarefa.getTarefaAtual().setPercentualConclusao(percentual));
    }


    public void adicionaProjeto(Projeto projeto) {
        if(!this.projetosGeridos.contains(projeto)) {
            this.projetosGeridos.add(projeto);
            projeto.setGestorDeProjeto(this);
        }
    }


}
