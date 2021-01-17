package pt.ufp.info.esof.sgp.dtos.creators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoCreateDTO implements CreateDTO<Empregado> {

    private String nome;
    private Cargo cargo;
    private String email;

    @Override
    public Empregado converter() {
        Empregado empregado = new Empregado();
        empregado.setNome(this.getNome());
        empregado.setEmail(this.getEmail());
        empregado.setCargo(this.getCargo());
        return empregado;
    }
}
