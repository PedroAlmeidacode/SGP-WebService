package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorDeProjetoTest {

    @Test
    void testAtribuirPercentualDeConclusao() {


        // caso Standart
        GestorDeProjeto gestorDeProjeto = new GestorDeProjeto();
        gestorDeProjeto.setNome("Hugo Vieira");
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Fazer UML");
        Projeto projeto = new Projeto();
        Empregado empregado = new Empregado();
        empregado.setNome("Ruben Jorge");

        tarefa.atribuirEmpregadoaTarefa(empregado);

        projeto.getTarefas().add(tarefa);
        gestorDeProjeto.getProjetosGeridos().add(projeto);

        gestorDeProjeto.atribuirPercentualDeConclusao(tarefa,20);
        assertEquals(20,tarefa.getTarefaAtual().getPercentualConclusao());







        // caso ele nao tenha esse projeto
        GestorDeProjeto gestorDeProjeto1 = new GestorDeProjeto();
        gestorDeProjeto1.setNome("Pedro Almeida");
        gestorDeProjeto1.atribuirPercentualDeConclusao(tarefa,30);
        assertEquals(20,tarefa.getTarefaAtual().getPercentualConclusao());








        // caso tenha o projeto mas nao tenha a tarefa
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Fazer diagrama de classes");
        tarefa1.atribuirEmpregadoaTarefa(empregado);
        tarefa1.getTarefaAtual().setPercentualConclusao(0);

        // esta a tentar adicinar percentual a uma tarefa que nao tem
        gestorDeProjeto.atribuirPercentualDeConclusao(tarefa1,100);
        // fica igual
        assertEquals(0, tarefa1.getTarefaAtual().getPercentualConclusao());


    }
}