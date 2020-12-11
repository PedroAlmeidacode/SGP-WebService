package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaDTO;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor<ProjetoResponseDTO, Projeto> {
    @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
        ProjetoResponseDTO responseDTO=new ProjetoResponseDTO();
        responseDTO.setCliente(projeto.getCliente());
        responseDTO.setNome(projeto.getNome());
        responseDTO.setTarefas(projeto.getTarefas().stream().peek(tarefa -> {
            tarefa.setDescricao(tarefa.getDescricao());
            tarefa.setDataIniciacao(tarefa.getDataIniciacao());
            tarefa.setTitulo(tarefa.getTitulo());
            tarefa.setDuracaoEstimada(tarefa.getDuracaoEstimada());
        }).collect(Collectors.toList()));
        return responseDTO;
    }



}
