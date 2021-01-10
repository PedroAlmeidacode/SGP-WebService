package pt.ufp.info.esof.sgp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.services.TarefaService;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        Tarefa tarefa= new Tarefa();
        tarefa.setTitulo("Tarefa de teste titulo");
        tarefa.setDescricao("Tarefa de teste descricao");
        tarefa.setDuracaoEstimada(3600);

        when(this.tarefaService.createTarefa(tarefa)).thenReturn(Optional.of(tarefa));

        String tarefaAsJasonString= objectMapper.writeValueAsString(tarefa);

        mockMvc.perform(post("/tarefa").content(tarefaAsJasonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        when(this.tarefaService.createTarefa(tarefa)).thenReturn(Optional.empty());
        Tarefa tarefaExistente= new Tarefa();
        tarefa.setTitulo("Tarefa de teste titulo");
        String tarefaExistenteAsJasonString= new ObjectMapper().writeValueAsString(tarefaExistente);

        mockMvc.perform(post("/tarefa").content(tarefaExistenteAsJasonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

    @Test
    void adicionaEmpregadoATarefa() throws Exception {

        //caso o resultado seja ok existe empregado existe tarefa e não existe empregado na tarefa
        Empregado empregado = new Empregado();
        empregado.setId(1L);
        empregado.setNome("juliana");

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa teste titulo");
        tarefa.setId(4L);

        when(this.tarefaService.adicionarEmpregado(4L,1L)).thenReturn(Optional.of(tarefa));

        mockMvc.perform(patch("/tarefa/4/empregado/1")).andExpect(status().isOk());

        //caso já exista um empregado na tarefa ou o empregado ou a tarefa não existam
        Empregado empregadoTeste = new Empregado();
        empregadoTeste.setId(2L);
        empregadoTeste.setNome("joao");
        when(this.tarefaService.adicionarEmpregado(4L,2L)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/tarefa/5/empregado/1")).andExpect(status().isNotFound());
    }

    @Test
    void adicionaPercentualTarefa() throws Exception {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa teste titulo apois");
        tarefa.setId(4L);
        //existe tarefa e o percentual tb é válido
        when(this.tarefaService.adicionarPercentualTarefa(4L,25f)).thenReturn(Optional.of(tarefa));
        mockMvc.perform(patch("/tarefa/4/percentual/25")).andExpect(status().isOk());


        Tarefa tarefaSec = new Tarefa();
        tarefaSec.setTitulo("Tarefa teste titulo apois");
        tarefaSec.setId(5L);
        //existe tarefa mas o percentual não é válido
        when(this.tarefaService.adicionarPercentualTarefa(5L,250f)).thenReturn(Optional.empty());
        mockMvc.perform(patch("/tarefa/5/percentual/250")).andExpect(status().isBadRequest());

    }

    @Test
    void adicionaTempoDedicadoTarefa() throws Exception {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa teste titulo apois");
        tarefa.setId(4L);

        when(this.tarefaService.adicionarTempoDedicadoTarefa(4L,35)).thenReturn(Optional.of(tarefa));
        mockMvc.perform(patch("/tarefa/4/tempoDedicado/35")).andExpect(status().isOk());

        //tarefa existe mas o tempo dedicado é negativo
        Tarefa tarefaSec = new Tarefa();
        tarefaSec.setTitulo("Tarefa teste titulo apois");
        tarefaSec.setId(5L);
        when(this.tarefaService.adicionarTempoDedicadoTarefa(5L,-35)).thenReturn(Optional.empty());
        mockMvc.perform(patch("/tarefa/5/tempoDedicado/-35")).andExpect(status().isBadRequest());

        //tarefa que não existe
        when(this.tarefaService.adicionarTempoDedicadoTarefa(6L,35)).thenReturn(Optional.empty());
        mockMvc.perform(patch("/tarefa/6/tempoDedicado/35")).andExpect(status().isBadRequest());

    }

}