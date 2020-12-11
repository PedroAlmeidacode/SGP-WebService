package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.Projeto;

import java.util.Optional;


@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long> {
    Optional<Projeto> findByNome(String nome);
}
