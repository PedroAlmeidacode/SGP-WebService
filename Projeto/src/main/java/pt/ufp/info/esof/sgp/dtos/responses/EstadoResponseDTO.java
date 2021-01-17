package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.models.enums.Estado;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponseDTO {
    private Estado estado;
}
