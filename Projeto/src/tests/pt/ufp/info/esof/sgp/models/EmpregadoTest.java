package pt.ufp.info.esof.sgp.models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

class EmpregadoTest {
    @Test
    void testIncluirTempoDedicado() {

        // teste caso primeira insercao de tempo na tarefa
        Empregado empregado = new Empregado();
        Tarefa tarefa = new Tarefa();
        tarefa.atribuirEmpregadoaTarefa(empregado);
        // 180 min = 3 horas
        empregado.incluirTempoDedicado(tarefa,180);
        assertEquals(180,tarefa.getTarefaAtual().getTempoDedicado());


        // teste de segunda insercao de tempo
        // trabalhou mais 3 horas (+3)
        empregado.incluirTempoDedicado(tarefa,180);
        assertEquals(360,tarefa.getTarefaAtual().getTempoDedicado());



        // teste de tentar adicionar a uma tarefa que nao é dele
        Tarefa tarefa2 = new Tarefa();
        Empregado empregado2 = new Empregado();
        tarefa2.atribuirEmpregadoaTarefa(empregado2);
        // empregado nao esta na tarefa logo nao pode adicionar tempo nela
        empregado.incluirTempoDedicado(tarefa,180);
        // tarefa deve ficar igual a como foi incializado no teste anterior
        assertEquals(360,tarefa.getTarefaAtual().getTempoDedicado());



    }
}