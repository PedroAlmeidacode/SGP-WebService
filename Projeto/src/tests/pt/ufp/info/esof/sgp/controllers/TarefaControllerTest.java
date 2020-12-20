package pt.ufp.info.esof.sgp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.sgp.services.ProjetoService;
import pt.ufp.info.esof.sgp.services.TarefaService;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(TarefaController.class)
class TarefaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createTarefa() throws Exception {

    }

    @Test
    void adicionaEmpregadoATarefa() throws Exception {

    }

    @Test
    void adicionaPercentualTarefa() throws Exception {

    }

    @Test
    void adicionaTempoDedicadoTarefa() throws Exception {

    }

}