package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.Optional;

public interface TarefaService {

    Optional<Tarefa> createTarefa(Tarefa converter);
}
