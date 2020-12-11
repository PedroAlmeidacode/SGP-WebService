package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Projeto;

import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> createProjeto(Projeto converter);
}
