package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.ClienteDTO;
import pt.ufp.info.esof.sgp.dtos.EstadoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaCreateDTO;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.util.stream.Collectors;

public class ConverterEstadoParaDTO implements Conversor<EstadoResponseDTO, Estado>{
    @Override
    public EstadoResponseDTO converter(Estado estado) {
        EstadoResponseDTO responseDTO=new EstadoResponseDTO();
        responseDTO.setEstado(estado);
        return responseDTO;
    }

}
