package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.GestorDeProjeto;

@Repository
public interface GestorDeProjetoRepository extends CrudRepository<GestorDeProjeto,Long> {
}
