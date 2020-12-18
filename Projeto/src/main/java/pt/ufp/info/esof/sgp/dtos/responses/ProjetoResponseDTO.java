package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;
import pt.ufp.info.esof.sgp.dtos.ClienteDTO;
import pt.ufp.info.esof.sgp.dtos.creators.TarefaCreateDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String nome;
    //TODO passar para emailCliente
    private ClienteDTO cliente;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();

}
