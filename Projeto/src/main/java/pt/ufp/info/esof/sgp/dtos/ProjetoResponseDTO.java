package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String nome;
    private ClienteDTO cliente;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();

}
