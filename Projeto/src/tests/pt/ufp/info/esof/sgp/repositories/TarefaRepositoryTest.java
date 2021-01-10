package pt.ufp.info.esof.sgp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TarefaRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private  EmpregadoRepository empregadoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    public void testeCricaoTarefa()
    {
        Cliente cliente= new Cliente();
        cliente.setNome("Clientito");
        cliente.setEmail("clientito@gmail.com");

        Projeto projeto = new Projeto();
        projeto.setNome("Projetito del testito");

        cliente.getProjetos().add(projeto);

        //antes de dar save ao cliente não deve de existir nem o cliente nem o projeto
        assertNull(cliente.getId());
        assertNull(projeto.getId());
        clienteRepository.save(cliente);

        assertEquals(1,clienteRepository.count());
        assertEquals(1,projetoRepository.count());

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefita del testito");
        tarefa.setDescricao("Describle del tarefita");
        tarefa.setDataIniciacao(LocalDateTime.now());

        Empregado empregado= new Empregado();
        empregado.setNome("Rubenito del Jorgito");
        empregado.setEmail("rubenito@gmail.com");
        empregado.setCargo(Cargo.DES_SENIOR);
        empregado.adicionarTarefa(tarefa);

        //antes de dar save no empregado ele não deve de existir
        assertNull(empregado.getId());
        empregadoRepository.save(empregado);

        //antes de guardar o projeto nada existe
        assertNull(tarefa.getId());
        assertNull(tarefa.getTarefaAtual().getId());
        projeto.getTarefas().add(tarefa);
        tarefaRepository.save(tarefa);
        assertEquals(1,tarefaRepository.count());
    }

}