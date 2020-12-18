package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.responses.EstadoResponseDTO;
import pt.ufp.info.esof.sgp.models.enums.Estado;

public class ConverterEstadoParaDTO implements Conversor<EstadoResponseDTO, Estado> {
    @Override
    public EstadoResponseDTO converter(Estado estado) {
        EstadoResponseDTO responseDTO = new EstadoResponseDTO();
        responseDTO.setEstado(estado);
        return responseDTO;
    }

}
