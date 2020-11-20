package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.sgp.models.enums.Cargo;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void testCalcularDuracao() {
        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Tarefa tarefa3 = new Tarefa();
        tarefa.setDuracaoEstimada(570);
        tarefa2.setDuracaoEstimada(450);
        tarefa3.setDuracaoEstimada(780);
        projeto.getTarefas().add(tarefa);
        projeto.getTarefas().add(tarefa2);
        projeto.getTarefas().add(tarefa3);

        assertEquals(projeto.calcularDuracao(),1800);

    }

    @Test
    void testCalcularCusto() {

        Projeto projeto = new Projeto();
        Tarefa tarefa = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Tarefa tarefa3 = new Tarefa();
        tarefa.setDuracaoEstimada(570);
        tarefa2.setDuracaoEstimada(450);
        tarefa3.setDuracaoEstimada(780);

        Empregado empregado = new Empregado();
        Empregado empregado1 = new Empregado();

        empregado.setCargo(Cargo.ANALISTA_JUNIOR);
        empregado1.setCargo(Cargo.DES_SENIOR);

        tarefa.atribuirEmpregadoaTarefa(empregado);
        tarefa2.atribuirEmpregadoaTarefa(empregado);
        tarefa3.atribuirEmpregadoaTarefa(empregado1);

        projeto.getTarefas().add(tarefa);
        projeto.getTarefas().add(tarefa2);
        projeto.getTarefas().add(tarefa3);

        assertEquals(projeto.calcularCusto(),860);
    }

    @Test
    void testCalcularEstado() {
        // serie de asserts de testar o estado do projeto
    }
}