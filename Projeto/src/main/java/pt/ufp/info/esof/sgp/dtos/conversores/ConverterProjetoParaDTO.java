package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.ClienteDTO;
import pt.ufp.info.esof.sgp.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaCreateDTO;
import pt.ufp.info.esof.sgp.models.Projeto;

import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor<ProjetoResponseDTO, Projeto> {
    @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
        ProjetoResponseDTO responseDTO=new ProjetoResponseDTO();
        responseDTO.setNome(projeto.getNome());

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setEmail(projeto.getCliente().getEmail());
        responseDTO.setCliente(clienteDTO);


        responseDTO.setTarefas(projeto.getTarefas().stream().map(tarefa -> {
            TarefaCreateDTO tarefaDTO=new TarefaCreateDTO();

            tarefaDTO.setDuracaoEstimada(tarefa.getDuracaoEstimada());
            tarefaDTO.setDescricao(tarefa.getDescricao());
            tarefaDTO.setTitulo(tarefa.getTitulo());
            return tarefaDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
