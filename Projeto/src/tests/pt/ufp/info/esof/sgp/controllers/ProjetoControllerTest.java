package pt.ufp.info.esof.sgp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.sgp.dtos.objectWithID.AdicionarTarefaAProjetoDTO;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.services.ProjetoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        // TODO not working

        // teste normal
        // objeto esperado pelo metodo do servico
        Projeto projeto = new Projeto();
        projeto.setNome("projeto11");
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        projeto.setCliente(cliente);

        // formato a enviar no post do objeto projetoDTO
        String projetoAsJsonString=new ObjectMapper().writeValueAsString(projeto);


        when(projetoService.createProjeto(projeto)).thenReturn(Optional.of(projeto));
        // post de criacao de projeto: reponse ok
        mockMvc.perform(post("/projeto").content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        // teste onde nome de projeto ja existe
        Projeto projeto2 = new Projeto();
        projeto2.setNome("projeto11");
        Cliente cliente2 = new Cliente();
        cliente2.setId(1L);
        projeto2.setCliente(cliente2);
        String projeto2AsJsonString=new ObjectMapper().writeValueAsString(projeto2);

        when(projetoService.createProjeto(projeto2)).thenReturn(Optional.empty());

        mockMvc.perform(post("/projeto").content(projeto2AsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());



    }

    @Test
    void adicionaTarefaAProjeto() throws Exception {
        // TODO fazer este
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("projeto1");
        AdicionarTarefaAProjetoDTO adicionarTarefaAProjetoDTO = new AdicionarTarefaAProjetoDTO();
        adicionarTarefaAProjetoDTO.setIdTarefa(1L);

        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(adicionarTarefaAProjetoDTO);

        when(projetoService.adicionarTarefa(1L,adicionarTarefaAProjetoDTO.converter())).thenReturn(Optional.of(projeto));

        mockMvc.perform(patch("/projeto/tarefa/1").content(tarefaAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    void getEstadoDescritivoProjeto() throws Exception {
        Projeto projeto=new Projeto();

        // chamada a metodo do servico pelo controlador retorna otional do objeto local
        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));
        // guarda response http da chamada do endpoint: expected ok response
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/estadoDescritivo")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        // teste: reposta nao e nula
        assertNotNull(httpResponseAsString);

        // projeto com id 2 nao foi inicializado em inicializacao logo obtera not found do controller
        mockMvc.perform(get("/projeto/2/estadoDescritivo")).andExpect(status().isNotFound());

    }

    @Test
    void getEstadoProjeto() throws Exception {
        Projeto projeto=new Projeto();

        // chamada a metodo do servico pelo controlador retorna optional de metodo do objeto local
        when(projetoService.getEstadoProjeto(1L)).thenReturn(Optional.of(projeto.calcularEstado()));
        // guarda response http da chamada do endpoint: expected ok response
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/estado")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        // teste: reposta nao e nula
        assertNotNull(httpResponseAsString);

        // projeto com id 2 nao foi inicializado em inicializacao logo obtera not found do controller
        mockMvc.perform(get("/projeto/2/estado")).andExpect(status().isNotFound());

    }

    @Test
    void getCustoProjeto() throws Exception {
        Projeto projeto=new Projeto();

        // chamada a metodo do servico pelo controlador retorna optional de metodo do objeto local
        when(projetoService.getCustoProjeto(1L)).thenReturn(Optional.of(projeto.calcularCusto()));
        // guarda response http da chamada do endpoint: expected ok response
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/custo")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        // teste: reposta nao e nula
        assertNotNull(httpResponseAsString);

        // projeto com id 2 nao foi inicializado em inicializacao logo obtera not found do controller
        mockMvc.perform(get("/projeto/2/custo")).andExpect(status().isNotFound());


    }

    @Test
    void getDuracaoProjeto() throws Exception {
        Projeto projeto=new Projeto();

        // chamada a metodo do servico pelo controlador retorna optional de metodo do objeto local
        when(projetoService.getDuracaoProjeto(1L)).thenReturn(Optional.of(projeto.calcularDuracao()));

        // guarda response http da chamada do endpoint: expected ok response
        String httpResponseAsString=mockMvc.perform(get("/projeto/1/duracao")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        // teste: reposta nao e nula
        assertNotNull(httpResponseAsString);
        // projeto com id 2 nao foi inicializado em inicializacao logo obtera not found do controller
        mockMvc.perform(get("/projeto/2/duracao")).andExpect(status().isNotFound());

    }

}