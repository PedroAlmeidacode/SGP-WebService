package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Empregado extends Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "empregado",cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;

    /**
     * incluir tempo dedicado para alem do incluido anteriormente
     * @param tempo  dedicado pelo empregado para esta tarefa
     * @param tarefa em execucao pelo empregado
     */
    protected void incluirTempoDedicado(Tarefa tarefa, int tempo) {
        // se o empregado que esta a trabalhar na tarefa for o que esta a tentar incluir
        if (tarefa.getEmpregado().equals(this)) {
            // ir à tarefa buscar a tarefa atual e buscar o tempo ja colocado
            int tempoAtual = tarefa.getTarefaAtual().getTempoDedicado();
            // adicionar mais o tempo
            tarefa.getTarefaAtual().setTempoDedicado(tempoAtual + tempo);
            // TODO refactorizar set tempo dedicado
        }
    }
}
