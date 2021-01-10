package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.responses.CustoResponseDTO;

import java.text.DecimalFormat;

public class ConverterCustoParaDTO implements Conversor<CustoResponseDTO, Double> {
    @Override
    public CustoResponseDTO converter(Double custo) {
        DecimalFormat df = new DecimalFormat("###.##");
        CustoResponseDTO responseDTO = new CustoResponseDTO();
        responseDTO.setCusto(df.format(custo));
        return responseDTO;
    }

}
