package pt.ufp.info.esof.sgp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.sgp.models.Cliente;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testeCriacaoDeCliente(){
        Cliente cliente = new Cliente();

        cliente.setNome("Pedro");

        // antes de salvar
        assertEquals(0,clienteRepository.count());

        this.clienteRepository.save(cliente);
        assertEquals(1,clienteRepository.count());
    }
}