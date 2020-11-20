package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.sgp.models.enums.Cargo;
import pt.ufp.info.esof.sgp.models.enums.Estado;

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

        Projeto projeto = new Projeto();

        //TAREFA ESTADO NORMAL
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();
        tarefa.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa.getTarefaAtual().setPercentualConclusao(50);
        // tempo dedicado = 500 min
        tarefa.getTarefaAtual().setTempoDedicado(500);


        //TAREFA ESTADO NORMAL
        Tarefa tarefa1 = new Tarefa();
        tarefa1.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa1.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa1.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa normal , diferencial de menos de 10 entre
        // a percentagem de tempo usada em relacao ao tempo que tem e a percentagem de conclusao
        tarefa1.getTarefaAtual().setTempoDedicado(580);


        //TAREFA ESTADO ATRASADA
        Tarefa tarefa2 = new Tarefa();
        tarefa2.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa2.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa2.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa atrasada visto que conclui menos do que o tempo que usou
        // e o diferencial entre o tempo usado e o feito é maior que 10
        tarefa2.getTarefaAtual().setTempoDedicado(700);


        //TAREFA ESTADO ADIANTADA
        Tarefa tarefa3 = new Tarefa();
        tarefa3.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa3.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa3.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa adiantada visto que conclui mais do que o tempo que usou
        tarefa3.getTarefaAtual().setTempoDedicado(400);


        // adiconar 4 tarefas ao projeto
        projeto.getTarefas().add(tarefa);
        projeto.getTarefas().add(tarefa1);
        projeto.getTarefas().add(tarefa2);
        projeto.getTarefas().add(tarefa3);

        assertEquals(Estado.NORMAL,projeto.getEstadoProjeto());

        // TODO teste calcular estado deu erro null


        // serie de asserts de testar o estado do projeto
    }
}