package pt.ufp.info.esof.sgp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {

    private final EmpregadoRepository empregadoRepository;

    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    @Override
    public Optional<Empregado> createEmpregado(Empregado empregado) {
        Optional<Empregado> optionalExplicador = empregadoRepository.findByEmail(empregado.getEmail());
        if (optionalExplicador.isEmpty()) {
            return Optional.of(empregadoRepository.save(empregado));
        }
        return Optional.empty();
    }


    @Override
    public List<Empregado> findAll() {
        List<Empregado> empregados = new ArrayList<>();
        empregadoRepository.findAll().forEach(empregados::add);
        return empregados;
    }
}
