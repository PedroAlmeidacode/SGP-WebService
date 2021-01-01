package pt.ufp.info.esof.sgp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.enums.Cargo;
import pt.ufp.info.esof.sgp.services.EmpregadoService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoService empregadoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createEmpregado() throws Exception {
        Empregado empregado = new Empregado();
        empregado.setCargo(Cargo.ANALISTA_JUNIOR);
        empregado.setNome("Hugo");
        empregado.setEmail("hugo123@gmail.com");

        // quando chamarmos o metodo queremos que retorne optional empregado
        when(this.empregadoService.createEmpregado(empregado)).thenReturn(Optional.of(empregado));

        String empregadoAsJsonString=new ObjectMapper().writeValueAsString(empregado);

        // teste de criar um novo empregado
        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        when(this.empregadoService.createEmpregado(empregado)).thenReturn(Optional.empty());
        Empregado empregadoExistente = new Empregado();
        empregadoExistente.setEmail("hugo123@gmail.com");
        String empregadoExistenteAsJsonString=new ObjectMapper().writeValueAsString(empregadoExistente);

        // teste de bad request pois empregado ja existe
        mockMvc.perform(post("/empregado").content(empregadoExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}