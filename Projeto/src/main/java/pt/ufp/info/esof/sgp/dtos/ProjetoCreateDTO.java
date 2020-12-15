package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.stream.Collectors;

@Data
public class ProjetoCreateDTO implements CreateDTO<Projeto>{
    private String nome;
    private Long clienteId;

    @Override
    public Projeto converter(){
        Projeto projeto = new Projeto();
        projeto.setNome(this.getNome());
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        projeto.setCliente(cliente);
        return projeto;
    }
}