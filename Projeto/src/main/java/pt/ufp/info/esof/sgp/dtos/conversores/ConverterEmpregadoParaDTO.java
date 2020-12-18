package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.EmpregadoCreateDTO;
import pt.ufp.info.esof.sgp.models.Empregado;


public class ConverterEmpregadoParaDTO implements Conversor<EmpregadoCreateDTO, Empregado> {

    @Override
    public EmpregadoCreateDTO converter(Empregado empregado) {
        EmpregadoCreateDTO responseDTO = new EmpregadoCreateDTO();
        responseDTO.setNome(empregado.getNome());
        responseDTO.setCargo(empregado.getCargo());
        responseDTO.setEmail(empregado.getEmail());
        return responseDTO;
    }

}
