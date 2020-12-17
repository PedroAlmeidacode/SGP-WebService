package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.CustoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.DuracaoResponseDTO;

public class ConverterDuracaoParaDTO implements Conversor<DuracaoResponseDTO, Integer>{
    @Override
    public DuracaoResponseDTO converter(Integer duracao) {
        DuracaoResponseDTO responseDTO=new DuracaoResponseDTO();
        responseDTO.setDuracao(duracao);
        return responseDTO;
    }
}
