package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.enums.Estado;

@Data
public class EstadoResponseDTO {
    private Estado estado;
}
