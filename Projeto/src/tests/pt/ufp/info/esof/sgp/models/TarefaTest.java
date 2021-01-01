package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.sgp.models.enums.Cargo;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void testGetCustoTarefa() {
        // empregado nao existe
        Tarefa tarefa = new Tarefa();
        assertEquals(tarefa.getCustoTarefa(),0);

        // empregado nao tem cargo atribuido
        Empregado empregado = new Empregado();
        empregado.adicionarTarefa(tarefa);
        assertEquals(tarefa.getCustoTarefa(),0);

        // caso normal Analista_junior
        tarefa.setDuracaoEstimada(60);
        empregado.setCargo(Cargo.ANALISTA_JUNIOR);
        assertEquals(tarefa.getCustoTarefa(),20);

        // caso normal Des_junior
        empregado.setCargo(Cargo.DES_JUNIOR);
        assertEquals(tarefa.getCustoTarefa(),10);

        // caso normal Des_senior
        empregado.setCargo(Cargo.DES_SENIOR);
        assertEquals(tarefa.getCustoTarefa(),40);

        // caso normal Analista_senior
        empregado.setCargo(Cargo.ANLISTA_SENIOR);
        assertEquals(tarefa.getCustoTarefa(),80);

        // TODO return 0 not reached
    }

    @Test
    void TestsetLocalDates() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTarefaAtual(new TarefaAtual());
        LocalDateTime now = LocalDateTime.now();
        tarefa.setDataIniciacao(now);
        tarefa.getTarefaAtual().setUltimaAtualizacao(now);

        // atualiza todas as datas para agora
        tarefa.setLocalDates();
        assertNotEquals(tarefa.getTarefaAtual().getUltimaAtualizacao(), now);
        assertNotEquals(tarefa.getDataIniciacao(), now);

    }

    @Test
    void testGetEstadoTarefa() {
        // esperado um estado de tarefa normal
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();
        empregado.adicionarTarefa(tarefa);
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
        empregado1.adicionarTarefa(tarefa1);
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
        empregado2.adicionarTarefa(tarefa2);
        tarefa2.setDuracaoEstimada(100);
        tarefa2.getTarefaAtual().setTempoDedicado(50);


        tarefa2.getTarefaAtual().setPercentualConclusao(50);

        // excede 10 minutos o prazo de finalizacao da tarefa que era de Tarefa.dataIniciacao + Tarefa.duracaoEstimada
        tarefa2.getTarefaAtual().setUltimaAtualizacao(tarefa2.getDataIniciacao().plusMinutes(110));
        assertEquals(Estado.ATRASADA, tarefa2.getEstadoTarefa());

    }

    @Test
    void testsetTempoDedicadoEmTarefaAtual() {
        // tempo negativo
        Empregado empregado = new Empregado();
        Tarefa tarefa = new Tarefa();
        empregado.adicionarTarefa(tarefa);
        tarefa.setTempoDedicadoEmTarefaAtual(-1);
        assertNotEquals(tarefa.getTarefaAtual().getTempoDedicado(),-1);
        // caso normal
        tarefa.setTempoDedicadoEmTarefaAtual(28);
        assertEquals(tarefa.getTarefaAtual().getTempoDedicado(), 28);
        // caso mais 22 que da 50
        tarefa.setTempoDedicadoEmTarefaAtual(22);
        assertEquals(tarefa.getTarefaAtual().getTempoDedicado(), 50);
    }
}