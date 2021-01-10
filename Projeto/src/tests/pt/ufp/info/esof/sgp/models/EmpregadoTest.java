package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpregadoTest {

    @Test
    void adicionarTarefaTest() {
        Empregado empregado = new Empregado();
        Tarefa tarefa = new Tarefa();


        // no caso normal
        empregado.adicionarTarefa(tarefa);
        assertEquals(empregado.getTarefas().size(),1);

        // nao resulta pois ja contem a tarefa
        empregado.adicionarTarefa(tarefa);
        assertEquals(empregado.getTarefas().size(),1);

    }
}