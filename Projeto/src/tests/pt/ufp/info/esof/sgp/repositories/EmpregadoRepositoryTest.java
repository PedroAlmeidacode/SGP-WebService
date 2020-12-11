package pt.ufp.info.esof.sgp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpregadoRepositoryTest {

    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    public void testeCriacaoDeEmpregado(){

        Empregado empregado = new Empregado();
        empregado.setNome("Ruben El'Jorge");
        empregado.setCargo(Cargo.ANALISTA_JUNIOR);


        Tarefa tarefa = new Tarefa();
        Tarefa tarefa1 = new Tarefa();
        tarefa.setDataIniciacao(LocalDateTime.now());

        empregado.adicionarTarefa(tarefa);
        empregado.adicionarTarefa(tarefa1);

        empregadoRepository.save(empregado);

        tarefaRepository.save(tarefa);
        tarefaRepository.save(tarefa1);

        assertEquals(2,tarefaRepository.count());
        assertEquals(1,empregadoRepository.count());



    }
}