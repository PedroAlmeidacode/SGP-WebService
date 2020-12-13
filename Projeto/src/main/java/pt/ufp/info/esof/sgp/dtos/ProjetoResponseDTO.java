package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Tarefa;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String nome;
    private ClienteDTO cliente;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();

}
