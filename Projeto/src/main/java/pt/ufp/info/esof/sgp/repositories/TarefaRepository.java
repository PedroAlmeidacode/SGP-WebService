package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.Tarefa;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa,Long> {
}
