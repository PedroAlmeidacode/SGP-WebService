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




    public void adicionarTarefa(Tarefa tarefa) {
        if (!this.tarefas.contains(tarefa)) {
            tarefas.add(tarefa);
            tarefa.setEmpregado(this);
            //cria tarefa atual
            tarefa.setLocalDates();
        }
    }
}
