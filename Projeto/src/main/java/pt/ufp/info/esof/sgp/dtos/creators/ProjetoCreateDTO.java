package pt.ufp.info.esof.sgp.dtos.creators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoCreateDTO implements CreateDTO<Projeto> {
    private String nome;
    private Long clienteId;

    @Override
    public Projeto converter() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.getNome());
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        projeto.setCliente(cliente);
        return projeto;
    }
}
