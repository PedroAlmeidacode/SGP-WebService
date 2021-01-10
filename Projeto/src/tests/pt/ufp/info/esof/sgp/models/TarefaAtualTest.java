package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class TarefaAtualTest {

    @Test
    void setPercentualConclusaoTest() {
        // teste para numeros negativos
        Tarefa tarefa = new Tarefa();
        tarefa.setTarefaAtual(new TarefaAtual());
        tarefa.getTarefaAtual().setPercentualConclusao(-1);
        assertNotEquals(tarefa.getTarefaAtual().getPercentualConclusao(), -1);

        // teste para numeros maiores que 100
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTarefaAtual(new TarefaAtual());
        tarefa1.getTarefaAtual().setPercentualConclusao(101);
        assertNotEquals(tarefa1.getTarefaAtual().getPercentualConclusao(), 101);
        // teste normal
        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTarefaAtual(new TarefaAtual());
        tarefa2.getTarefaAtual().setPercentualConclusao(80);
        assertEquals(tarefa2.getTarefaAtual().getPercentualConclusao(), 80);

    }


    @Test
    void setUltimaAtualizacaoParaAgoraTest() {
        // teste normal
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTarefaAtual(new TarefaAtual());

        LocalDateTime date = LocalDateTime.now();
        tarefa1.getTarefaAtual().setUltimaAtualizacao(date);
        assertEquals(tarefa1.getTarefaAtual().getUltimaAtualizacao(),date);

        tarefa1.getTarefaAtual().setUltimaAtualizacaoParaAgora();
        assertNotEquals(tarefa1.getTarefaAtual().getUltimaAtualizacao().plusMinutes(2),date);


    }

}


