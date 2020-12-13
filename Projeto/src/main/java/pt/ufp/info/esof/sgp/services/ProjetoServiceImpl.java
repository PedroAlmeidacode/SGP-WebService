package pt.ufp.info.esof.sgp.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.repositories.ClienteRepository;
import pt.ufp.info.esof.sgp.repositories.ProjetoRepository;
import pt.ufp.info.esof.sgp.repositories.TarefaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{
    private final ClienteRepository clienteRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaRepository tarefaRepository;

    public ProjetoServiceImpl(ClienteRepository clienteRepository, ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
        this.clienteRepository = clienteRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public Optional<Projeto> createProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto=projetoRepository.findByNome(projeto.getNome());
        if(optionalProjeto.isEmpty())
        {
            Optional<Cliente> optionalCliente=clienteRepository.findById(projeto.getCliente().getId());
            if(optionalCliente.isPresent()){ // caso ele exista na BD
                Cliente cliente=optionalCliente.get();
                cliente.adicionaProjeto(projeto);
                projeto.setCliente(cliente);
                clienteRepository.save(optionalCliente.get());
            }
            return Optional.of(projetoRepository.save(projeto));

        }
        return Optional.empty();
    }

    // TODO resolver porblema de duplicacao em criacaoa de projet5o
    // TODO ver se tarefa ja esta atribuida a outro projeto ao adicionar tarefa a projeto


    @Override
    public Optional<Projeto> adicionarTarefa(Long idProjeto, Tarefa tarefa) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(idProjeto);

        if(optionalProjeto.isPresent()){
            Projeto projeto=optionalProjeto.get();
            int quantidadeDTarefasAntes=projeto.getTarefas().size();
            Optional<Tarefa> optionalTarefa = tarefaRepository.findById(tarefa.getId());
            if(optionalTarefa.isPresent()) {

                Tarefa t = optionalTarefa.get();
                projeto.adicionarTarefa(t);

            }else {
                // tarefa nao existe
                return Optional.empty();
            }

            int quantidadedeTarefasDepois=projeto.getTarefas().size();
            if(quantidadeDTarefasAntes!=quantidadedeTarefasDepois) {
                return Optional.of(projetoRepository.save(projeto));
            }
            // nao adicionou a tarefa a projeto
            return Optional.empty();
        }
        // projeto nao existe
        return Optional.empty();
    }
}
