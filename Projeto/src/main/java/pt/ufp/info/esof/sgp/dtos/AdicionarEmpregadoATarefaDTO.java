package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Empregado;


@Data
public class AdicionarEmpregadoATarefaDTO implements CreateDTO<Empregado> {

    private Long idEmpregado;


    @Override
    public Empregado converter(){
        Empregado empregado = new Empregado();
        empregado.setId(this.idEmpregado);
        return empregado;
    }
}
