package pt.ufp.info.esof.sgp.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void createEmpregado(){

        // TODO fazer

    }

}