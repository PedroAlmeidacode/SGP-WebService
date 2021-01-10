package pt.ufp.info.esof.sgp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.models.TarefaAtual;
import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
        String titulo = "tarefa 1";
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);

        // teste normal
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.createTarefa(tarefa).isPresent());


        // caso em que a tarefa ja existe na bd
        when(tarefaRepository.findByTitulo(titulo)).thenReturn(Optional.of(tarefa));
        assertTrue(tarefaService.createTarefa(tarefa).isEmpty());

    }


    @Test
    void adicionarEmpregado(){
        Empregado empregado = new Empregado();
        empregado.setId(1L);
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);


        // caso em que a tarefa nao existe na bd
        assertTrue(tarefaService.adicionarEmpregado(1L, 1L).isEmpty());

        // caso em que o empregado nao existe na bd mas a tarefa existe
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        // caso normal
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));
        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.adicionarEmpregado(1L, 1L).isPresent());

        // caso em que tarefa ja tem empregado atribuido
        assertTrue(tarefaService.adicionarEmpregado(1L, 1L).isEmpty());


    }

    @Test
    void adicionarPercentualTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTarefaAtual(new TarefaAtual());

        // caso normal
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.adicionarPercentualTarefa(1L, (float)20).isPresent());

        // percentual passado maior que 100 ou menor que 0
        assertTrue(tarefaService.adicionarPercentualTarefa(1L, (float)110).isEmpty());

        // caso a tarefa nao exista na bd
        assertTrue(tarefaService.adicionarPercentualTarefa(2L, (float)10).isEmpty());

    }

    @Test
    void adicionarTempoDedicadoTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTarefaAtual(new TarefaAtual());

        // caso normal
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        assertTrue(tarefaService.adicionarTempoDedicadoTarefa(1L, 20).isPresent());

        // tempo passado menor que 0
        assertTrue(tarefaService.adicionarTempoDedicadoTarefa(1L, -10).isEmpty());

        // caso a tarefa nao exista na bd
        assertTrue(tarefaService.adicionarTempoDedicadoTarefa(2L, 10).isEmpty());

    }
}