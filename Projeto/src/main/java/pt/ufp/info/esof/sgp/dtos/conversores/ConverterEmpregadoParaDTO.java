package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.sgp.models.Empregado;


public class ConverterEmpregadoParaDTO implements Conversor<EmpregadoResponseDTO, Empregado>{

    @Override
    public EmpregadoResponseDTO converter(Empregado empregado){
        EmpregadoResponseDTO responseDTO = new EmpregadoResponseDTO();
        responseDTO.setCargo(empregado.getCargo());
        responseDTO.setEmail(empregado.getEmail());
        return responseDTO;
    }

}
