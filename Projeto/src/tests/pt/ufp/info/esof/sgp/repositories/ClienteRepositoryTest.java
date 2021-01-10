package pt.ufp.info.esof.sgp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;


    @Test
    public void testeCriacaoDeCliente(){
        Cliente cliente = new Cliente();

        cliente.setNome("Pedro");
        cliente.setEmail("pedrocas@gmail.com");

        Projeto projeto = new Projeto();
        projeto.setNome("Carro Voador do Futuro");
        projeto.setCliente(cliente);

        cliente.getProjetos().add(projeto);
        // antes de salvar
        assertNull(cliente.getId());
        assertNull(projeto.getId());
        this.clienteRepository.save(cliente);
        assertEquals(1,clienteRepository.count());
        assertEquals(1,projetoRepository.count());

    }

}