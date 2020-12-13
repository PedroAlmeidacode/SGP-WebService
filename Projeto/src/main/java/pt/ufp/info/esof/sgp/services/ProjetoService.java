package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> createProjeto(Projeto converter);

    Optional<Projeto> adicionarTarefa(Long idProjeto, Tarefa converter);
}
