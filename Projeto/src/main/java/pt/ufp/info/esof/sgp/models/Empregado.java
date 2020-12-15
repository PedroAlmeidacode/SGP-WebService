package pt.ufp.info.esof.sgp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Empregado extends Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "empregado")
    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;
    private String email;

    /**
     * incluir tempo dedicado para alem do incluido anteriormente
     * @param tempo  dedicado pelo empregado para esta tarefa
     * @param tarefa em execucao pelo empregado
     */
    protected void incluirTempoDedicado(Tarefa tarefa, int tempo) {
        // se o empregado que esta a trabalhar na tarefa for o que esta a tentar incluir
        if (tarefa.getEmpregado().equals(this)) {
            // ir à tarefa buscar a tarefa atual e buscar o tempo ja colocado
            if(tempo <= 0) return;
            tarefa.setTempoDedicadoEmTarefaAtual(tempo);
        }
    }


    public void adicionarTarefa(Tarefa tarefa){
        if(!this.tarefas.contains(tarefa)){
            tarefas.add(tarefa);
            tarefa.setEmpregado(this);
            //cria tarefa atual
            tarefa.atribuirEmpregadoaTarefa(this);
        }
    }


}
