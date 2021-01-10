package pt.ufp.info.esof.sgp.services;

import pt.ufp.info.esof.sgp.models.Empregado;

import java.util.Optional;

public interface EmpregadoService {

    Optional<Empregado> createEmpregado(Empregado converter);
}
