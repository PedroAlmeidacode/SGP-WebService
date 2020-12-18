package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.DuracaoResponseDTO;

import java.time.Period;

public class ConverterDuracaoParaDTO implements Conversor<DuracaoResponseDTO, Integer> {
    @Override
    public DuracaoResponseDTO converter(Integer duracao) {
        System.out.println(duracao);
        DuracaoResponseDTO responseDTO = new DuracaoResponseDTO();

        responseDTO.setDuracao(duracao/24/60 + " dias, " + duracao/60%24 + " horas, " + duracao%60 + " minutos");
        return responseDTO;
    }
}
