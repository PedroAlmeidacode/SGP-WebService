package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void testGetCustoTarefa() {
        // TODO teste do metodo getCustoTarefa
    }

    @Test
    void testAtribuirEmpregadoaTarefa() {
        // TODO teste do metodo atribuirEmpregadoaTarefa

    }

    @Test
    void testGetEstadoTarefa() {
        // esperado um estado de tarefa normal
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();
        tarefa.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa.getTarefaAtual().setPercentualConclusao(50);
        // tempo dedicado = 500 min
        tarefa.getTarefaAtual().setTempoDedicado(500);
        // percentagem de tempo usado = 500 * 100 / 1000 = 50% (diferencial 50 - 50 = 0)
        assertEquals(Estado.NORMAL, tarefa.getEstadoTarefa());


        // esperado um estado de tarefa normal , diferencial de menos de 10 entre
        // a percentagem de tempo usada em relacao ao tempo que tem e a percentagem de conclusao
        tarefa.getTarefaAtual().setTempoDedicado(580);
        // percentagem de tempo usado = 580 * 100 / 1000 = 58% (diferencial 58-50 = 8)
        assertEquals(Estado.NORMAL, tarefa.getEstadoTarefa());


        // esperado um estado de tarefa atrasada visto que conclui menos do que o tempo que usou
        // e o diferencial entre o tempo usado e o feito é maior que 10
        tarefa.getTarefaAtual().setTempoDedicado(700);
        // percentagem de tempo usado = 700 * 100 / 1000 = 70% (diferencial 70-50 = 20)
        assertEquals(Estado.ATRASADA, tarefa.getEstadoTarefa());


        // esperado um estado de tarefa adiantada visto que conclui mais do que o tempo que usou
        tarefa.getTarefaAtual().setTempoDedicado(400);
        // percentagem de tempo usado = 400 * 100 / 1000 = 40% (40 < 50-> percentual de concluao)
        assertEquals(Estado.ADIANTADA, tarefa.getEstadoTarefa());


        // esperando um estado de tarefa concluida visto que o seu estado de conclusao e 100
        tarefa.getTarefaAtual().setPercentualConclusao(100);
        assertEquals(Estado.CONCLUIDA, tarefa.getEstadoTarefa());


        // esperando um estado de tarefa "TAREFA_NAO_ATRBUIDA"
        Tarefa tarefa1 = new Tarefa();
        assertEquals(Estado.TAREFA_NAO_ATRBUIDA, tarefa1.getEstadoTarefa());


        // existem duas formas de a tarefa estar atrasada por exesso de tempo
        // 1 - o tempo dedicado pelo empregado é maior do que o tempo previsto
        // 2 - tarefaAtual.ultimaAtualizacao > Tarefa.dataIniciacao + Tarefa.duracaoEstimada


        // 1 - esperando um esatado de tarefa atrasado pois
        // a tarefa nao foi concluida dentro da duracao estimada

        Empregado empregado1 = new Empregado();
        tarefa1.atribuirEmpregadoaTarefa(empregado1);
        // duaracao estimada = 100 min
        tarefa1.setDuracaoEstimada(100);
        tarefa1.getTarefaAtual().setPercentualConclusao(50);
        // tempo dedicado = 500 min
        tarefa1.getTarefaAtual().setTempoDedicado(500);
        assertEquals(Estado.ATRASADA, tarefa1.getEstadoTarefa());


        // 2 - esperando um esatado de tarefa atrasado pois
        // a ultima do gestor e depois do prazo previsto
        Tarefa tarefa2 = new Tarefa();
        Empregado empregado2 = new Empregado();
        tarefa2.atribuirEmpregadoaTarefa(empregado2);
        tarefa2.setDuracaoEstimada(100);
        tarefa2.getTarefaAtual().setTempoDedicado(50);

        // gestor
        GestorDeProjeto gp = new GestorDeProjeto();
        // experimental
        gp.atribuirPercentualDeConclusao(tarefa2, 50);

        // excede 10 minutos o prazo de finalizacao da tarefa que era de Tarefa.dataIniciacao + Tarefa.duracaoEstimada
        tarefa2.getTarefaAtual().setUltimaAtualizacao(tarefa2.getDataIniciacao().plusMinutes(110));
        assertEquals(Estado.ATRASADA, tarefa2.getEstadoTarefa());

    }
}