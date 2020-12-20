package pt.ufp.info.esof.sgp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.sgp.services.EmpregadoService;
import pt.ufp.info.esof.sgp.services.ProjetoService;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createProjeto() throws Exception {

    }

    @Test
    void adicionaTarefaAProjeto() throws Exception {

    }

    @Test
    void getEstadoDescritivoProjeto() throws Exception {

    }

    @Test
    void getEstadoProjeto() throws Exception {

    }

    @Test
    void getCustoProjeto() throws Exception {

    }

    @Test
    void getDuracaoProjeto() throws Exception {

    }

}