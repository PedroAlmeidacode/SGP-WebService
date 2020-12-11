package pt.ufp.info.esof.sgp.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.repositories.ClienteRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService{

    private final TarefaRepository tarefaRepository;

    public TarefaServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public Optional<Tarefa> createTarefa(Tarefa tarefa) {
        Optional<Tarefa> optionalTarefa=tarefaRepository.findByTitulo(tarefa.getTitulo());
        if(optionalTarefa.isEmpty()){
            return Optional.of(tarefaRepository.save(tarefa));
        }
        return Optional.empty();
    }
}
