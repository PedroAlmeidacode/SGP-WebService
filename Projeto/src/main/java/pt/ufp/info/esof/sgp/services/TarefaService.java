package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.Optional;

public interface TarefaService {

    Optional<Tarefa> createTarefa(Tarefa converter);

    Optional<Tarefa> adicionarEmpregado(Long idTarefa, Empregado converter);

    Optional<Tarefa> adicionarPercentualTarefa(Long idTarefa, Float percentual);

    Optional<Tarefa> adicionarTempoDedicadoTarefa(Long idTarefa, int tempoDedicado);

}
