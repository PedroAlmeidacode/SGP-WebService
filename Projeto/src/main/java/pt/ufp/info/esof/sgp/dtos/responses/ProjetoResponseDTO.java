package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.dtos.ClienteDTO;
import pt.ufp.info.esof.sgp.dtos.creators.TarefaCreateDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoResponseDTO {
    private String nome;
    private ClienteDTO cliente;
    private List<TarefaCreateDTO> tarefas = new ArrayList<>();

}
