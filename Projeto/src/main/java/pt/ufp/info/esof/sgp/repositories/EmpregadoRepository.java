package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.Empregado;

import java.util.Optional;

@Repository
public interface EmpregadoRepository extends CrudRepository<Empregado,Long> {
    Optional<Empregado> findByEmail(String email);
}
