package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.models.enums.Estado;


import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> findById(Long idProjeto);

    Optional<Projeto> createProjeto(Projeto converter);

    Optional<Projeto> adicionarTarefa(Long idProjeto, Long idTarefa);


    Optional<Estado> getEstadoProjeto(Long idProjeto);

    Optional<Double> getCustoProjeto(Long idProjeto);

    Optional<Integer> getDuracaoProjeto(Long idProjeto);
}