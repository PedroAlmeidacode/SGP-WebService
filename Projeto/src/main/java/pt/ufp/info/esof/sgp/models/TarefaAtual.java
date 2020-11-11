package pt.ufp.info.esof.sgp.models;

import java.time.LocalDateTime;

public class TarefaAtual {

    private Tarefa tarefa;
    private int tempoDedicado; //minutos ---> empregado irá por isto
    private float percentualConclusao; // % é o gestor do projeto que irá dar este percentual
    private LocalDateTime ultimaAtualizacao;

}
