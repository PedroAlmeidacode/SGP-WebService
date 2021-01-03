package pt.ufp.info.esof.sgp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.repositories.ClienteRepository;
import pt.ufp.info.esof.sgp.repositories.ProjetoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest {


    @Autowired
    private ProjetoService projetoService;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaRepository tarefaRepository;

    @Test
    void createProjeto(){
        String nome = "projeto teste";
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.adicionaProjeto(projeto);

        // caso normal o projeto ainda nao existe
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        // salva o cliente e or cascata salva o projeto associado a este
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        assertTrue(projetoService.createProjeto(projeto).isPresent());


        // caso onde o cliente nao existe
        Projeto projeto1 = new Projeto();
        projeto1.setNome("existe projeto");
        // tem que cliente mas o cliente nao existe nao existe na base de dados
        Cliente cliente1 = new Cliente();
        cliente1.setId(2L);
        cliente1.adicionaProjeto(projeto1);
        assertTrue(projetoService.createProjeto(projeto1).isEmpty());



        // caso em que o projeto ja existe
        projeto.setNome(nome);
        when(projetoRepository.findByNome(nome)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.createProjeto(projeto).isEmpty());



    }

    @Test
    void adicionarTarefa(){
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);

        // caso normal em que tarefa e projeot existem na bd
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertTrue(projetoService.adicionarTarefa(1L,1L).isPresent());

        // caso em que o projeto nao existe (e aparaentemente tarefa tambem nao mas o teste nao chega la)
        assertTrue(projetoService.adicionarTarefa(2L,2L).isEmpty());


        Projeto projeto1 = new Projeto();
        projeto1.setId(2L);

        // caso em que a tarefa nao existe
        when(projetoRepository.findById(2L)).thenReturn(Optional.of(projeto1));
        assertTrue(projetoService.adicionarTarefa(2L,2L).isEmpty());


        // teste em que quantidade de tarefas nao aumenta
        // TODO cant test that


    }

    @Test
    void findById(){
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent());
        assertTrue(projetoService.findById(2L).isEmpty());


    }

    @Test
    void getEstadoProjeto(){
        // TODO fazer


    }

    @Test
    void getCustoProjeto(){
        // TODO fazer


    }


    @Test
    void getDuracaoProjeto(){
        // TODO fazer


    }

}