package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.Projeto;


@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long> {
}
