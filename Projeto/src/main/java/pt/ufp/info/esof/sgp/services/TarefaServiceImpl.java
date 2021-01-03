package pt.ufp.info.esof.sgp.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.sgp.models.*;

import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;

import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    private final EmpregadoRepository empregadoRepository;

    public TarefaServiceImpl(TarefaRepository tarefaRepository, EmpregadoRepository empregadoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.empregadoRepository = empregadoRepository;
    }

    @Override
    //cria uma tarefa raw
    public Optional<Tarefa> createTarefa(Tarefa tarefa) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findByTitulo(tarefa.getTitulo());
        if (optionalTarefa.isEmpty()) {
            return Optional.of(tarefaRepository.save(tarefa));
        }
        return Optional.empty();
    }

    @Override
    // função que vai fazer a agregação do empregado na tarefa e vice-versa
    public Optional<Tarefa> adicionarEmpregado(Long idTarefa, Long idEmpregado) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(idTarefa);

        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            // em caso da tarefa já possuir um empregado
            if(tarefa.getEmpregado()!=null) {
                return Optional.empty();
            }
            else {
                Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idEmpregado);
                if (optionalEmpregado.isPresent()) {
                    Empregado e = optionalEmpregado.get();
                    // se o empregado nao contiver a tarefa e se a tarefa nao contiver empregado atribuido
                    if (!e.getTarefas().contains(tarefa) && tarefa.getEmpregado() == null) {
                        // associacao entre os dois em models e criacao de tarefa atual entre outros
                        e.adicionarTarefa(tarefa);
                        empregadoRepository.save(e);
                        return Optional.of(tarefaRepository.save(tarefa));
                    }
                }
                // nao tem empregado que esta a tentar atribuir
            }
            return Optional.empty();
        }
        // nao tem tarefa com o id passado
        return Optional.empty();
    }

    @Override
    // esta função vai dar patch ao percentual da tarefa atual da tarefa
    public Optional<Tarefa> adicionarPercentualTarefa(Long idTarefa, Float percentual) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(idTarefa);
        if (optionalTarefa.isPresent())  //caso a taref exista na BD
        {
            Tarefa tarefa = optionalTarefa.get();               //vai buscar a tarefa ao optional
            TarefaAtual tarefaAtual = tarefa.getTarefaAtual();   //vai buscar a atual da tarefa

            if(tarefaAtual.setPercentualConclusao(percentual))     //modifica o percentual
                return Optional.of(tarefaRepository.save(tarefa));  //dá save á tarefa
            return Optional.empty();
        }
        return Optional.empty(); // caso essa tarefa não exista
    }

    @Override
    // esta função vai dar patch ao tempo dedicado do empregado na tarefa atual da tarefa
    public Optional<Tarefa> adicionarTempoDedicadoTarefa(Long idTarefa, int tempoDedicado) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(idTarefa);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            // caso return false quer dizer que o tempodedicado seja negativo
            if(tarefa.setTempoDedicadoEmTarefaAtual(tempoDedicado))
                return Optional.of(tarefaRepository.save(tarefa));
            return Optional.empty();
        }
        return Optional.empty();
    }
}