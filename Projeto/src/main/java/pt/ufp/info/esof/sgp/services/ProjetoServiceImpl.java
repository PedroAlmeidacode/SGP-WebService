package pt.ufp.info.esof.sgp.services;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.repositories.ClienteRepository;
import pt.ufp.info.esof.sgp.repositories.ProjetoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{
    private final ClienteRepository clienteRepository;
    private final ProjetoRepository projetoRepository;

    public ProjetoServiceImpl(ClienteRepository clienteRepository, ProjetoRepository projetoRepository) {
        this.clienteRepository = clienteRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    public Optional<Projeto> createProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto=projetoRepository.findByNome(projeto.getNome());
        if(optionalProjeto.isEmpty()){
            projetoRepository.save(projeto);
            Cliente cliente = projeto.getCliente();
            Optional<Cliente> optionalCliente=clienteRepository.findByNome(projeto.getCliente().getNome());
            if(optionalCliente.isPresent()){
                cliente.adicionaProjeto(projeto);
                clienteRepository.save(optionalCliente.get());
            }
            projeto.setCliente(cliente);
            return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
    }

}
