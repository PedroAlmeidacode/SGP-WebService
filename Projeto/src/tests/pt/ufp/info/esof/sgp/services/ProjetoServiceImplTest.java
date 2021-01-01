package pt.ufp.info.esof.sgp.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.repositories.ClienteRepository;
import pt.ufp.info.esof.sgp.repositories.ProjetoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import static org.junit.jupiter.api.Assertions.*;

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
        // TODO fazer


    }

    @Test
    void adicionarTarefa(){
        // TODO fazer

    }

    @Test
    void findById(){
        // TODO fazer


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