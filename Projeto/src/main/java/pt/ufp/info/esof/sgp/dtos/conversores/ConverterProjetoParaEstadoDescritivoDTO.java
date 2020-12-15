package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.*;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.stream.Collectors;

public class ConverterProjetoParaEstadoDescritivoDTO implements Conversor<EstadoDescritivoProjetoDTO, Projeto> {

    @Override
    public EstadoDescritivoProjetoDTO converter(Projeto projeto){

        EstadoDescritivoProjetoDTO responseDTO = new EstadoDescritivoProjetoDTO();

        // percorre cada tarefa do projeto
        responseDTO.setEstadoTarefas(projeto.getTarefas().stream().map(tarefa -> {
            EstadoTarefaDTO tarefaDTO=new EstadoTarefaDTO();

            //insere dados para preencher EstadoTarefaDTO para cada tarefa
            tarefaDTO.setNomeEmpregado(tarefa.getEmpregado().getNome());
            tarefaDTO.setDuracaoEstimada(tarefa.getDuracaoEstimada());
            tarefaDTO.setDataIniciacao(tarefa.getDataIniciacao());
            tarefaDTO.setTitulo(tarefa.getTitulo());
            tarefaDTO.setPercentualConclusao(tarefa.getTarefaAtual().getPercentualConclusao());
            tarefaDTO.setEstadoTarefa(tarefa.getEstadoTarefa());
            return tarefaDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
