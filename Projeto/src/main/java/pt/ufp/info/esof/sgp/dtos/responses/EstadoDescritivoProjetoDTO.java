package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDescritivoProjetoDTO {
    private List<EstadoTarefaDTO> estadoTarefas = new ArrayList<>();
}
