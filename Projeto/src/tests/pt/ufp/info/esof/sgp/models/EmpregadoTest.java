package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class EmpregadoTest {
    @Test
    void testIncluirTempoDedicado() {

        // teste caso primeira insercao de tempo na tarefa
        Empregado empregado = new Empregado();
        Tarefa tarefa = new Tarefa();
        tarefa.atribuirEmpregadoaTarefa(empregado);
        // 180 min = 3 horas
        empregado.incluirTempoDedicado(tarefa, 180);
        assertEquals(180, tarefa.getTarefaAtual().getTempoDedicado());


        // teste de segunda insercao de tempo
        // trabalhou mais 2 horas (+2)
        empregado.incluirTempoDedicado(tarefa, 120);
        assertEquals(300, tarefa.getTarefaAtual().getTempoDedicado());


        // teste de tentar adicionar a uma tarefa que nao é dele
        Tarefa tarefa2 = new Tarefa();
        tarefa.setTitulo("Estudar algo");
        Empregado empregado2 = new Empregado();
        empregado2.setNome("Joao");
        tarefa2.atribuirEmpregadoaTarefa(empregado2);
        // empregado nao esta na tarefa logo nao pode adicionar tempo nela
        empregado2.incluirTempoDedicado(tarefa, 180);
        // tarefa deve ficar igual a como foi incializado no teste anterior
        assertEquals(300, tarefa.getTarefaAtual().getTempoDedicado());


    }
}