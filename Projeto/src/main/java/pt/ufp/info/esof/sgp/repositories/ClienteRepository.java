package pt.ufp.info.esof.sgp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.sgp.models.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String nome);
}
