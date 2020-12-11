package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

@Data
public class EmpregadoCreateDTO implements CreateDTO<Empregado> {

    private Cargo cargo;
    private String email;

    @Override
    public Empregado converter(){
        Empregado empregado = new Empregado();
        empregado.setEmail(this.getEmail());
        empregado.setCargo(this.getCargo());
        return empregado;
    }
}
