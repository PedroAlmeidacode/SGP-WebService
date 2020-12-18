package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EstadoDescritivoProjetoDTO {
    private List<EstadoTarefaDTO> estadoTarefas = new ArrayList<>();

}
