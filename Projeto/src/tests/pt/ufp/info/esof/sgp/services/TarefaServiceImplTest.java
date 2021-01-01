package pt.ufp.info.esof.sgp.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TarefaServiceImpl.class)
class TarefaServiceImplTest {

    @Autowired
    private TarefaService tarefaService;
    @MockBean
    private TarefaRepository tarefaRepository;
    @MockBean
    private EmpregadoRepository empregadoRepository;



    @Test
    void createTarefa(){

        // TODO test
    }


    @Test
    void adicionarEmpregado(){
        // TODO test


    }

    @Test
    void adicionarPercentualTarefa(){

        // TODO test

    }

    @Test
    void adicionarTempoDedicadoTarefa(){


        // TODO test

    }
}